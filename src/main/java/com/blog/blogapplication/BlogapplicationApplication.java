package com.blog.blogapplication;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.blogapplication.entities.Role;
import com.blog.blogapplication.repositories.RoleRepo;

@SpringBootApplication
public class BlogapplicationApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogapplicationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		Role role = new Role();
		role.setId(501);
		role.setName("ROLE_ADMIN");

		Role role2 = new Role();
		role2.setId(502);
		role2.setName("ROLE_USER");

		List<Role> roles = List.of(role2,role);
		roleRepo.saveAll(roles);
	}

}
