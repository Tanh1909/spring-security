package com.example.springSecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String lastName;
    private String firstName;

    @ManyToMany
    private Set<Role> roles;
}
