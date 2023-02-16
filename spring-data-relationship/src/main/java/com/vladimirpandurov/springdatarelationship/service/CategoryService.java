package com.vladimirpandurov.springdatarelationship.service;


import com.vladimirpandurov.springdatarelationship.dto.requestDto.CategoryRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.CategoryResponseDto;
import com.vladimirpandurov.springdatarelationship.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(Long categoryId);
    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(Long categoryId);
    List<CategoryResponseDto> getCategories();
    CategoryResponseDto deleteCategory(Long categoryId);
    CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
}
