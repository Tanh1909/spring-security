package com.example.springSecurity.controller;

import com.example.springSecurity.dto.response.ApiResponse;
import com.example.springSecurity.entity.Permission;
import com.example.springSecurity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Permission permission){
        return ResponseEntity.ok(ApiResponse.success(permissionService.create(permission)));
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ApiResponse.success(permissionService.findAll()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        permissionService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Delete Successfully"));
    }

}
