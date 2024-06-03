package com.example.springSecurity.controller;

import com.example.springSecurity.dto.request.UserUpdateRequest;
import com.example.springSecurity.dto.response.ApiResponse;
import com.example.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable Long id,@RequestBody UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(ApiResponse.success(userService.patchUpdate(id,userUpdateRequest)));
    }


}
