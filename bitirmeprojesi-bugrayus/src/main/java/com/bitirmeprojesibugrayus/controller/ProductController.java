package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.request.CreateProductRequestModel;
import com.bitirmeprojesibugrayus.model.request.UpdateProductRequestModel;
import com.bitirmeprojesibugrayus.model.response.CategoryResponseModel;
import com.bitirmeprojesibugrayus.model.response.ProductResponseModel;
import com.bitirmeprojesibugrayus.service.CategoryService;
import com.bitirmeprojesibugrayus.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<Boolean>> createProduct(@RequestBody CreateProductRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(productService.createProduct(requestModel)));
    }

    @GetMapping("/product")
    public ResponseEntity<ApiResponse<List<ProductResponseModel>>> findAllProducts() {
        return ResponseEntity.ok(ApiResponse.of(productService.findAllProducts()));
    }

    @GetMapping("/product/id/{id}")
    public ResponseEntity<ApiResponse<ProductResponseModel>> findProductById(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(productService.findProductById(id)));
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponseModel>>> findProductByCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(ApiResponse.of(productService.findProductByCategory(categoryId)));
    }

    @GetMapping("/product/priceBetween")
    public ResponseEntity<ApiResponse<List<ProductResponseModel>>> findProductByCategory(@RequestParam BigDecimal priceLow, BigDecimal priceHigh) {
        return ResponseEntity.ok(ApiResponse.of(productService.findProductByPriceBetween(priceLow, priceHigh)));
    }

    @PutMapping("/product")
    public ResponseEntity<ApiResponse<Boolean>> updateProduct(@RequestBody UpdateProductRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(productService.updateProduct(requestModel)));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(productService.deleteProduct(id)));
    }
}
