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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
import com.blog.blogapplication.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId),HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
        @RequestParam(value = "pageNumber" , defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" , defaultValue = "5" , required = false) Integer pageSize
    ){

        PostResponse allPost = postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable("userId") Integer userId,
        @RequestParam(value = "pageNumber" , defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" , defaultValue = "5" , required = false) Integer pageSize)
    {
        return new ResponseEntity<PostResponse>(postService.getAllPostByUser(userId,pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable("categoryId") Integer categoryId,
        @RequestParam(value = "pageNumber" , defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" , defaultValue = "5" , required = false) Integer pageSize)
    {
        return new ResponseEntity<PostResponse>(postService.getAllPostByCategory(categoryId,pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable("postId") Integer postId){
        return new ResponseEntity<PostDto>(postService.getSinglePost(postId),HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted Successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/category/{categoryId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto newPostDto ,@PathVariable Integer postId, @PathVariable Integer categoryId){
        PostDto updatePost = postService.updatePost(newPostDto, postId, categoryId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
    
}
