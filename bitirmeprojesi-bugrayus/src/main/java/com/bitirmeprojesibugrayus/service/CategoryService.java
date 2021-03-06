package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.model.Category;
import com.bitirmeprojesibugrayus.model.Product;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.request.UpdateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.response.CategoryDetailsResponseModel;
import com.bitirmeprojesibugrayus.model.response.CategoryResponseModel;
import com.bitirmeprojesibugrayus.repository.CategoryRepository;
import com.bitirmeprojesibugrayus.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final AuthenticationService authenticationService;

    public boolean createCategory(CreateCategoryRequestModel requestModel) {
        if (requestModel.getTaxPercent().compareTo(BigDecimal.ZERO) < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tax cannot be less than 0");
        if (categoryRepository.existsByName(requestModel.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists.");
        Category category = mapper.map(requestModel, Category.class);
        category.setCreatedBy(authenticationService.getCurrentUser());
        category.setUpdatedBy(authenticationService.getCurrentUser());
        categoryRepository.save(category);
        return true;
    }

    public List<CategoryResponseModel> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, CategoryResponseModel.class))
                .collect(Collectors.toList());
    }

    public CategoryResponseModel findCategoryById(long id) {
        if (!categoryRepository.existsById(id))
            return null;
        return mapper.map(categoryRepository.getById(id), CategoryResponseModel.class);
    }

    public boolean updateCategory(UpdateCategoryRequestModel requestModel) {
        if (!categoryRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found by that id");
        Category category = categoryRepository.getById(requestModel.getId());
        if (!Objects.equals(category.getName(), requestModel.getName()) && categoryRepository.existsByName(requestModel.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists.");
        if (!Objects.equals(category.getTaxPercent(), requestModel.getTaxPercent())) {
            List<Product> products = productRepository.findProductByCategoryId(category.getId());
            for (Product product :
                    products) {
                BigDecimal priceWithoutTax = product.getPrice().subtract(product.getTax());
                product.setPrice(priceWithoutTax.add(requestModel.getTaxPercent().multiply(priceWithoutTax).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)));
                product.setTax(requestModel.getTaxPercent().multiply(priceWithoutTax).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN));
                productRepository.save(product);
            }
        }
        mapper.map(requestModel, category);
        category.setUpdatedBy(authenticationService.getCurrentUser());
        categoryRepository.save(category);
        return true;
    }

    public boolean deleteCategory(long id) {
        if (!categoryRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found by that id");
        if (productRepository.existsByCategoryId(id))
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Category has products, it cannot be deleted.");
        categoryRepository.deleteById(id);
        return true;
    }

    public List<CategoryDetailsResponseModel> findCategoryDetails() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDetailsResponseModel> categoryDetails = new ArrayList<>();
        for (Category category :
                categories) {
            List<Product> products = productRepository
                    .findProductByCategoryId(category.getId());
            if (products.isEmpty())
                continue;
            BigDecimal minPrice =
                    products
                            .stream()
                            .min(Comparator.comparing(Product::getPrice))
                            .orElseThrow(NoSuchElementException::new)
                            .getPrice();
            BigDecimal maxPrice =
                    products
                            .stream()
                            .max(Comparator.comparing(Product::getPrice))
                            .orElseThrow(NoSuchElementException::new)
                            .getPrice();
            BigDecimal[] bigDecimals =
                    products
                            .stream()
                            .map(Product::getPrice)
                            .filter(Objects::nonNull)
                            .map(bd -> new BigDecimal[]{bd, BigDecimal.ONE})
                            .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
                            .orElse(null);
            assert bigDecimals != null;
            BigDecimal averagePrice = bigDecimals[0].divide(bigDecimals[1], RoundingMode.HALF_EVEN);
            int productCount = products.size();
            CategoryDetailsResponseModel responseModel = new CategoryDetailsResponseModel();
            responseModel.setCategoryName(category.getName());
            responseModel.setMinimumPrice(minPrice);
            responseModel.setMaximumPrice(maxPrice);
            responseModel.setAveragePrice(averagePrice);
            responseModel.setTaxPercent(category.getTaxPercent());
            responseModel.setProductCount(productCount);
            categoryDetails.add(responseModel);
        }
        return categoryDetails;
    }
}
