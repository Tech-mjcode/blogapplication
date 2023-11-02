package com.blog.blogapplication.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
    @Id
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();
}
