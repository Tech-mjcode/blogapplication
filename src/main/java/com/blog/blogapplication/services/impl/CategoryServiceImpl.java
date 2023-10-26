package com.blog.blogapplication.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.payloads.CategoryDto;
import com.blog.blogapplication.repositories.CategoryRepo;
import com.blog.blogapplication.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        Category save = categoryRepo.save(category);
        return modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto newCategoryDto, Integer oldCatId) {
        Category oldCategory = categoryRepo.findById(oldCatId).orElseThrow(()->new ResourceNotFoundException("Category", "id", oldCatId));
        
        oldCategory.setCategoryTitle(newCategoryDto.getCategoryTitle());
        oldCategory.setCategoryDescription(newCategoryDto.getCategoryDescription());
        categoryRepo.save(oldCategory);

        return modelMapper.map(oldCategory, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id ", categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id ", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> findAll = categoryRepo.findAll();
        List<CategoryDto> collectList = findAll.stream().map((cat)-> modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return collectList;
    }
}
