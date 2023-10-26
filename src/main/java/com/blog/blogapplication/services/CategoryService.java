package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.payloads.CategoryDto;

public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto newCategoryDto , Integer oldCatId);
    //delete
    void deleteCategory(Integer categoryId);
    //get
    CategoryDto getCategory(Integer categoryId);
    //getAll
    List<CategoryDto> getAllCategory();
}
