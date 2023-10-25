package com.blog.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    
}
