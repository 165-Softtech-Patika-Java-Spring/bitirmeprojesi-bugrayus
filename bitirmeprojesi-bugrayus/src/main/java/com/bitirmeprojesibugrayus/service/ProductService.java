package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.model.Category;
import com.bitirmeprojesibugrayus.model.Product;
import com.bitirmeprojesibugrayus.model.request.CreateProductRequestModel;
import com.bitirmeprojesibugrayus.model.request.UpdateProductRequestModel;
import com.bitirmeprojesibugrayus.model.response.ProductResponseModel;
import com.bitirmeprojesibugrayus.repository.CategoryRepository;
import com.bitirmeprojesibugrayus.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final AuthenticationService authenticationService;

    public ProductResponseModel findProductById(long id) {
        if (!productRepository.existsById(id))
            return null;
        return mapper.map(productRepository.getById(id), ProductResponseModel.class);
    }

    public boolean createProduct(CreateProductRequestModel requestModel) {
        if (requestModel.getPriceWithoutTax().compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product cannot be less or equal to 0");
        Product product = new Product();
        Category category = categoryRepository.getById(requestModel.getCategoryId());
        product.setPrice(requestModel.getPriceWithoutTax().add(category.getTaxPercent().multiply(requestModel.getPriceWithoutTax()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)));
        product.setCategory(category);
        product.setTax(category.getTaxPercent().multiply(requestModel.getPriceWithoutTax()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN));
        product.setName(requestModel.getName());
        product.setCreatedBy(authenticationService.getCurrentUser());
        product.setUpdatedBy(authenticationService.getCurrentUser());
        productRepository.save(product);
        return true;
    }

    public List<ProductResponseModel> findAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, ProductResponseModel.class))
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(long id) {
        if (!productRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by that id");
        productRepository.deleteById(id);
        return true;
    }

    public boolean updateProduct(UpdateProductRequestModel requestModel) {
        if (!productRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by that id");
        Product product = productRepository.getById(requestModel.getId());
        Category category = categoryRepository.getById(requestModel.getCategoryId());
        product.setPrice(requestModel.getPriceWithoutTax().add(category.getTaxPercent().multiply(requestModel.getPriceWithoutTax()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)));
        product.setCategory(category);
        product.setTax(
                category
                        .getTaxPercent()
                        .multiply(requestModel.getPriceWithoutTax())
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)
        );
        product.setName(requestModel.getName());
        product.setUpdatedBy(authenticationService.getCurrentUser());
        productRepository.save(product);
        return true;
    }

    public List<ProductResponseModel> findProductByCategory(long categoryId) {
        return productRepository.findProductByCategoryId(categoryId).stream().map(product -> mapper.map(product, ProductResponseModel.class)).collect(Collectors.toList());
    }

    public List<ProductResponseModel> findProductByPriceBetween(BigDecimal priceLow, BigDecimal priceHigh) {
        return productRepository.findProductByPriceBetween(priceLow, priceHigh).stream().map(product -> mapper.map(product, ProductResponseModel.class)).collect(Collectors.toList());
    }
}
