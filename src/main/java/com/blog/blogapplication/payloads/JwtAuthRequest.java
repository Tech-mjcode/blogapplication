package com.blog.blogapplication.payloads;

import javax.annotation.sql.DataSourceDefinition;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
