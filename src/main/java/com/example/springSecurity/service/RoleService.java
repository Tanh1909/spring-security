package com.example.springSecurity.service;

import com.example.springSecurity.dto.request.RoleCreateRequest;
import com.example.springSecurity.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(RoleCreateRequest roleCreateRequest);
    void deleteById(String name);
    List<Role> findAll();
}
