package com.blog.blogapplication.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.entities.User;
import java.util.List;


public interface PostRepo extends JpaRepository<Post,Integer>{
    
    //find all post of particular user
    Page<Post> findByUser(User user,Pageable p);
    
    //find all post by particular category
    Page<Post> findByCategory(Category category,Pageable p);

    List<Post> findByTitleContaining(String keyword);
}
