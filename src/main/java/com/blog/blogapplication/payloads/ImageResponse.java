package com.blog.blogapplication.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageResponse {
    private String fileName;
    private boolean isUpload;
}
