package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.model.Category;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public boolean create(CreateCategoryRequestModel requestModel) {
        if (categoryRepository.existsByName(requestModel.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists.");
//        mapper.getConfiguration().setAmbiguityIgnored(true);
        categoryRepository.save(mapper.map(requestModel, Category.class));
        return true;
    }
}
