package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<Boolean>> create(@RequestBody CreateCategoryRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(categoryService.create(requestModel)));
    }
}
