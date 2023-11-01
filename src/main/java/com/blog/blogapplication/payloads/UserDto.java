package com.blog.blogapplication.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.blog.blogapplication.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    
    private int id;
    
    @Size(min = 5 , message = "User Name must be min of 5 characters !!")
    private String name;
    
    @Email(message = "Email address is not valid !!")
    private String email;

    @Size(min = 3 , max = 10 ,message = "Password must be 3-10 characters")
    private String password;
    
    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
