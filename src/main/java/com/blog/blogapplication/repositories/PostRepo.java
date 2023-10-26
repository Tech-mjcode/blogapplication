package com.blog.blogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
    
    //find all post of particular user
    List<Post> findByUser(User user);

    //find all post by particular category
    List<Post> findByCategory(Category category);
}
