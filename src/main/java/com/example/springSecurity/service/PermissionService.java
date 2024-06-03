package com.example.springSecurity.service;

import com.example.springSecurity.entity.Permission;

import java.util.List;

public interface PermissionService {
    Permission create(Permission permission);
    void deleteById(String name);
    List<Permission> findAll();
}
