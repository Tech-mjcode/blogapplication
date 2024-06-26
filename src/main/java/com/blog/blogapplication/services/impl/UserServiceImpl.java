package com.blog.blogapplication.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.entities.Role;
import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.payloads.UserDto;
import com.blog.blogapplication.repositories.RoleRepo;
import com.blog.blogapplication.repositories.UserRepo;
import com.blog.blogapplication.services.UserService;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired 
    RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        Role role = roleRepo.findByName("ROLE_USER");
        User user = dtoToUser(userDto);
        user.setRoles(Set.of(role));
        User saveUser = this.userRepo.save(user);
        return userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);

        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUser = this.userRepo.findAll();
        List<UserDto> userDtos = allUser.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }
    
    // public User dtoToUser(UserDto userDto){
    //     User user = new User();
    //     user.setId(userDto.getId());
    //     user.setName(userDto.getName());
    //     user.setPassword(userDto.getPassword());
    //     user.setAbout(userDto.getAbout());
    //     user.setEmail(userDto.getEmail());
    //     return user;
    // }

    //using modelmapper above code

       public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    // public UserDto userToDto(User user){
    //     UserDto userDto = new UserDto();
    //     userDto.setId(user.getId());
    //     userDto.setName(user.getName());
    //     userDto.setEmail(user.getEmail());
    //     userDto.setPassword(user.getPassword());
    //     userDto.setAbout(user.getAbout());
    //     return userDto;
    // } 

    public UserDto userToDto(User user){
       return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //roles
        Role roleUser = roleRepo.findByName("ROLE_USER");
        user.getRoles().add(roleUser);
        User saveUser = userRepo.save(user);
        return modelMapper.map(saveUser,UserDto.class);
    } 


}
