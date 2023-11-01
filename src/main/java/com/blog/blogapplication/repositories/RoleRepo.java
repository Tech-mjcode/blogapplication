package com.blog.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
