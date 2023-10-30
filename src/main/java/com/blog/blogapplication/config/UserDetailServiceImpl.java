package com.blog.blogapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.exception.ResourceNotFoundException;
import com.blog.blogapplication.repositories.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException(" User ", "email with "+username, 0));
        CustomUserDetails customUserDetails  = new CustomUserDetails(user);
        return customUserDetails;
        
    }
    
}
