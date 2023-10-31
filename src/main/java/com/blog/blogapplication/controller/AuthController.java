package com.blog.blogapplication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.config.UserDetailServiceImpl;
import com.blog.blogapplication.entities.User;
import com.blog.blogapplication.payloads.JwtAuthRequest;
import com.blog.blogapplication.payloads.JwtAuthResponse;
import com.blog.blogapplication.repositories.UserRepo;
import com.blog.blogapplication.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request ) throws Exception{
        
        authenticated(request.getUserName(), request.getPassword());
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(request.getUserName());
        String generateToken = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(generateToken);
        return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);

    }

    private void authenticated(String username , String password) throws Exception{
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch(BadCredentialsException e){
            System.out.println("invalid login detatils");
            throw new Exception("invalid usrname or password");
        }
        
       
        
    }
}
