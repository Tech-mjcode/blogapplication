package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;

public interface PostService {
    
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto newPostDto, Integer postId);
    void deletePost(Integer postId);
    PostDto getSinglePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy , String SortDir);
    PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize);
    PostResponse getAllPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);
    List<PostDto> searchPost(String keyword);




}
