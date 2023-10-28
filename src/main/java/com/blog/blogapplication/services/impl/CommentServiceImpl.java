package com.blog.blogapplication.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.entities.Comment;
import com.blog.blogapplication.entities.Post;
import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.payloads.CommentDto;
import com.blog.blogapplication.repositories.CommentRepo;
import com.blog.blogapplication.repositories.PostRepo;
import com.blog.blogapplication.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "id", postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        return modelMapper.map(commentRepo.save(comment),CommentDto.class);
        
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        commentRepo.delete(comment);
    }
    
}
