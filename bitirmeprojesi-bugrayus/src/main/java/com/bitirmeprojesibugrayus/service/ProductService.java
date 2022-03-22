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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public ProductResponseModel findProductById(long id) {
        if (!productRepository.existsById(id))
            return null;
        return mapper.map(productRepository.getById(id), ProductResponseModel.class);
    }

    public boolean createProduct(CreateProductRequestModel requestModel) {
        Product product = new Product();
        Category category = categoryRepository.getById(requestModel.getCategoryId());
        product.setPrice(requestModel.getPriceWithoutTax().add(category.getKDV().multiply(requestModel.getPriceWithoutTax())));
        product.setCategory(category);
        product.setTax(category.getKDV().multiply(requestModel.getPriceWithoutTax()));
        product.setName(requestModel.getName());
        productRepository.save(product);
        return true;
    }

    public List<ProductResponseModel> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(x -> mapper.map(x, ProductResponseModel.class)).collect(Collectors.toList());
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
        product.setPrice(requestModel.getPrice());
        productRepository.save(product);
        return true;
    }
}
