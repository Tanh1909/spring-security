package com.example.springSecurity.service;

import com.example.springSecurity.entity.User;

public interface AuthService {
    String login(User user);
    User register(User user);
    void logout(String token);
}
