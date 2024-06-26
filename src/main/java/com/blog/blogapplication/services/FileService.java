package com.blog.blogapplication.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadImage(String path , MultipartFile mFile) throws IOException;
    InputStream getUploadedImage(String path,String fileName) throws IOException;
}
