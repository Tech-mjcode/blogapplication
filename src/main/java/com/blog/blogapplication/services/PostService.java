package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.payloads.PostDto;

public interface PostService {
    
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto newPostDto, Integer postId , Integer categoryId);
    
    
    void deletePost(Integer postId);
    PostDto getSinglePost(Integer postId);
    List<PostDto> getAllPost(Integer pageNumber , Integer pageSize);
    List<PostDto> getAllPostByUser(Integer userId);
    List<PostDto> getAllPostByCategory(Integer categoryId);
    List<PostDto> searchPost(String keyword);




}
