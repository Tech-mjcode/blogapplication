package com.blog.blogapplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.entities.Category;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
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
    public PostDto updatePost(PostDto newPostDto, Integer postId, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(newPostDto.getTitle());
        post.setContent(newPostDto.getContent());
        post.setCategory(category);

        return modelMapper.map(postRepo.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getSinglePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber , Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> findAllPostByPage = postRepo.findAll(pageable);
        List<Post> content = findAllPostByPage.getContent();
        List<PostDto> allPostDto = content.stream().map((p)-> modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        
        
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDto);
        postResponse.setPageNumber(findAllPostByPage.getNumber());
        postResponse.setPageSize(findAllPostByPage.getSize());
        postResponse.setTotalRow(findAllPostByPage.getTotalElements());
        postResponse.setTotalPage(findAllPostByPage.getTotalPages());
        postResponse.setFirstPage(findAllPostByPage.isFirst());
        postResponse.setLastPage(findAllPostByPage.isLast());
        postResponse.setEmpty(findAllPostByPage.isEmpty());
        return postResponse;
    }

    @Override
    public PostResponse getAllPostByUser(Integer userId , Integer pageNumber, Integer pageSize) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        
        Page<Post> pageOfPost = postRepo.findByUser(user, pageable);
        List<PostDto> allPostDto = pageOfPost.stream().map((p)-> modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDto);
        postResponse.setPageNumber(pageOfPost.getNumber());
        postResponse.setPageSize(pageOfPost.getSize());
        postResponse.setTotalRow(pageOfPost.getTotalElements());
        postResponse.setTotalPage(pageOfPost.getTotalPages());
        postResponse.setFirstPage(pageOfPost.isFirst());
        postResponse.setLastPage(pageOfPost.isLast());
        postResponse.setEmpty(pageOfPost.isEmpty());
        
        return postResponse;
    }

    @Override
    public PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<Post> findPostPage = postRepo.findByCategory(category,pageable);
        List<PostDto> allListPostDto = findPostPage.stream().map((p)-> modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allListPostDto);
        postResponse.setPageNumber(findPostPage.getNumber());
        postResponse.setPageSize(findPostPage.getSize());
        postResponse.setTotalPage(findPostPage.getTotalPages());
        postResponse.setTotalRow(findPostPage.getTotalElements());
        postResponse.setEmpty(findPostPage.isEmpty());
        postResponse.setFirstPage(findPostPage.isFirst());
        postResponse.setLastPage(findPostPage.isLast());
        
        return postResponse;
    }

    @Override
    public PostResponse searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }
    
}
