package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;

public interface PostService {
    
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto newPostDto, Integer postId , Integer categoryId);
    
    
    void deletePost(Integer postId);
    PostDto getSinglePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber , Integer pageSize);
    PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize);
    PostResponse getAllPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);
    PostResponse searchPost(String keyword);




}
