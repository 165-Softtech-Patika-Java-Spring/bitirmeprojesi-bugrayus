package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.model.Category;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.request.UpdateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.response.CategoryResponseModel;
import com.bitirmeprojesibugrayus.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public boolean createCategory(CreateCategoryRequestModel requestModel) {
        if (categoryRepository.existsByName(requestModel.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists.");
//        mapper.getConfiguration().setAmbiguityIgnored(true);
        categoryRepository.save(mapper.map(requestModel, Category.class));
        return true;
    }

    public List<CategoryResponseModel> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(x -> mapper.map(x, CategoryResponseModel.class)).collect(Collectors.toList());
    }

    public CategoryResponseModel findCategoryById(long id) {
        if (!categoryRepository.existsById(id))
            return null;
        return mapper.map(categoryRepository.getById(id), CategoryResponseModel.class);
    }

    public boolean updateCategory(UpdateCategoryRequestModel requestModel) {
        if (!categoryRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by that id");
        Category category = categoryRepository.getById(requestModel.getId());
        mapper.map(requestModel, category);
        categoryRepository.save(category);
        return true;
    }

    public boolean deleteCategory(long id) {
        if (!categoryRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found by that id");
        categoryRepository.deleteById(id);
        return true;
    }
}
