package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.request.UpdateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.response.CategoryResponseModel;
import com.bitirmeprojesibugrayus.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<Boolean>> createCategory(@RequestBody CreateCategoryRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(categoryService.createCategory(requestModel)));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<CategoryResponseModel>>> findAllCategories() {
        return ResponseEntity.ok(ApiResponse.of(categoryService.findAllCategories()));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseModel>> findCategoryById(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(categoryService.findCategoryById(id)));
    }

    @PutMapping("/category")
    public ResponseEntity<ApiResponse<Boolean>> updateCategory(@RequestBody UpdateCategoryRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(categoryService.updateCategory(requestModel)));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCategory(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(categoryService.deleteCategory(id)));
    }
}
