package com.blog.blogapplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.repositories.CategoryRepo;
import com.blog.blogapplication.repositories.PostRepo;
import com.blog.blogapplication.repositories.UserRepo;
import com.blog.blogapplication.services.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "id", categoryId));
        
        //from postdto only get post title and content
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        
        Post savePost = postRepo.save(post);

        return modelMapper.map(savePost, PostDto.class);
        
    }

    @Override
    public PostDto updatePost(PostDto newPostDto, Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public PostDto getSinglePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> findAll = postRepo.findAll();

        List<PostDto> allPostDto = findAll.stream().map((p)-> modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        return allPostDto;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        List<Post> findByUser = postRepo.findByUser(user);

        List<PostDto> allPostDto = findByUser.stream().map((p)-> modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        return allPostDto;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> findByCategory = postRepo.findByCategory(category);

        List<PostDto> allListPostDto = findByCategory.stream().map((p)-> modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        return allListPostDto;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }
    
}
