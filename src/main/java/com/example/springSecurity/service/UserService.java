package com.example.springSecurity.service;

import com.example.springSecurity.dto.request.UserUpdateRequest;
import com.example.springSecurity.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    User findByUsername(String username);
    User patchUpdate(Long id,UserUpdateRequest reqUser);
    List<User> findAll();

}
