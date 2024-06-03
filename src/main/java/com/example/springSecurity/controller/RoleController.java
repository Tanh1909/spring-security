package com.example.springSecurity.controller;

import com.example.springSecurity.dto.request.RoleCreateRequest;
import com.example.springSecurity.dto.response.ApiResponse;
import com.example.springSecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoleCreateRequest roleCreateRequest){
        return ResponseEntity.ok(ApiResponse.success(roleService.create(roleCreateRequest)));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ApiResponse.success(roleService.findAll()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        roleService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Delete Successfully"));
    }
}
