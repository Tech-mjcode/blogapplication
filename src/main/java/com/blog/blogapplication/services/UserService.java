package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.payloads.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
