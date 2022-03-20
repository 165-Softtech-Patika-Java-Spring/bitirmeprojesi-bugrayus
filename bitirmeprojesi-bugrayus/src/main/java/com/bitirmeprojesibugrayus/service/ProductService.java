package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

//    public ProductResponseModel getProduct(long id) {
//        if (!productRepository.existsById(id))
//            return null;
//        return mapper.map(productRepository.getById(id), ProductResponseModel.class);
//    }
//
//    public boolean createProduct(CreateProductRequestModel requestModel) {
//        mapper.getConfiguration().setAmbiguityIgnored(true);
//        productRepository.save(mapper.map(requestModel, Product.class));
//        return true;
//    }
//
//    public List<ProductResponseModel> getProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream().map(x -> mapper.map(x, ProductResponseModel.class)).collect(Collectors.toList());
//    }
//
//    public boolean deleteProduct(long id) {
//        if (!productRepository.existsById(id))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by that id");
//        productRepository.deleteById(id);
//        return true;
//    }
//
//    public boolean updateProduct(UpdateProductRequestModel requestModel) {
//        if (!productRepository.existsById(requestModel.getId()))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by that id");
//        Product product = productRepository.getById(requestModel.getId());
//        product.setPrice(requestModel.getPrice());
//        productRepository.save(product);
//        return true;
//    }
}
