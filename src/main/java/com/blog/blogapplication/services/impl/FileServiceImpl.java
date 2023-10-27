package com.blog.blogapplication.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogapplication.services.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile mFile) throws IOException {


        String fileType = mFile.getContentType();
        String name = path + File.separator + mFile.getOriginalFilename();
        System.out.println(fileType);
        String randomName = UUID.randomUUID().toString();

        String finalFileName = randomName.concat(name.substring(name.lastIndexOf(".")));

        String fullPath = path + File.separator + finalFileName;

        File f = new File(path);
        if(!f.exists())
            f.mkdir();
        
        Files.copy(mFile.getInputStream(), Paths.get(fullPath));
        return finalFileName;
    }

    @Override
    public InputStream getUploadedImage(String path, String fileName) throws IOException{
        
        InputStream inputStream = new FileInputStream(path+File.separator+fileName);
        return inputStream;
    }
    
}
