package com.blog.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.CategoryDto;
import com.blog.blogapplication.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto cDto){
        return new ResponseEntity<CategoryDto>(categoryService.createCategory(cDto),HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto cDto, @PathVariable("catId") Integer catId){
        return new ResponseEntity<CategoryDto>(categoryService.updateCategory(cDto,catId),HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("catId") Integer catId){
        categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully", true),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCat(@PathVariable("catId") Integer catId){
        return new ResponseEntity<CategoryDto>(categoryService.getCategory(catId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAll(){
        return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategory(),HttpStatus.OK);
    }
}
