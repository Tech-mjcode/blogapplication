package com.blog.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
