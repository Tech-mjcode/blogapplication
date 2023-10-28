package com.blog.blogapplication.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class PostDto {
    private Integer postId;
    @Size(min = 5,message = "Post Title must be minimum 5 character")
    private String title;
    @Size(min = 5,message = "Post content must be minimum 5 character")
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new HashSet<>();
}
