package com.blog.blogapplication.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogapplication.config.AppConstants;
import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.ImageResponse;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
import com.blog.blogapplication.services.FileService;
import com.blog.blogapplication.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    String path;


    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId),HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
        @RequestParam(value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
        @RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY , required = false) String sortBy,
        @RequestParam(value = "sortDirction", defaultValue = AppConstants.SORT_DIR , required = false) String sortDirection
    ){

        PostResponse allPost = postService.getAllPost(pageNumber, pageSize,sortBy,sortDirection);
        return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable("userId") Integer userId,
        @RequestParam(value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize)
    {
        return new ResponseEntity<PostResponse>(postService.getAllPostByUser(userId,pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable("categoryId") Integer categoryId,
        @RequestParam(value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize)
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

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto newPostDto,@PathVariable Integer postId){
        PostDto updatePost = postService.updatePost(newPostDto, postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
    
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> search(@PathVariable String keyword){
        return new ResponseEntity<>(postService.searchPost(keyword),HttpStatus.OK);
    }

    @PostMapping("/image/upload/posts/{postId}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("file") MultipartFile mFile, @PathVariable Integer postId){
        try {
            
            PostDto singlePost = postService.getSinglePost(postId);
            String uploadImage = fileService.uploadImage(path, mFile);
            singlePost.setImageName(uploadImage);
            postService.updatePost(singlePost, postId);
            return new ResponseEntity<>(new ImageResponse("File Uploaded Successfully", true),HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ImageResponse("Image is not uploaded Successfully",false),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/image/{imageName}")
    public void downloadImage(@PathVariable String imageName , HttpServletResponse response) throws IOException{
        
        InputStream file = fileService.getUploadedImage(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(file, response.getOutputStream());
        
    }
}
