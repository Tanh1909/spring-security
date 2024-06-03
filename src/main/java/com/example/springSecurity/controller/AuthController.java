package com.example.springSecurity.controller;

import com.example.springSecurity.dto.response.ApiResponse;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        return ResponseEntity.ok(ApiResponse.success(authService.register(user)));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return ResponseEntity.ok(ApiResponse.success(authService.login(user)));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader String authorization){
        authService.logout(authorization);
        return ResponseEntity.ok(ApiResponse.success("You are logged out!"));
    }
}
