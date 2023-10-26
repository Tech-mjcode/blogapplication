package com.blog.blogapplication.payloads;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @Size(min = 5 , message = "Category Title minimum 5 character")
    private String categoryTitle;

    @Size(min = 5 , message = "Category Description minimum 5 character")
    private String categoryDescription;
}
