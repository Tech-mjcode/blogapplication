package com.blog.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
