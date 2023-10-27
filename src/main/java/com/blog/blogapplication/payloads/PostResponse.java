package com.blog.blogapplication.payloads;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalRow;
    private int totalPage;
    private boolean firstPage;
    private boolean lastPage;
    private boolean Empty;
}
