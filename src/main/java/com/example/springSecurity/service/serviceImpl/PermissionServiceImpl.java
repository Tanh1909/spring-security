package com.example.springSecurity.service.serviceImpl;

import com.example.springSecurity.entity.Permission;
import com.example.springSecurity.exception.AppException;
import com.example.springSecurity.exception.ErrorCode;
import com.example.springSecurity.repository.PermissionRepository;
import com.example.springSecurity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(String name) {
        Permission permission=permissionRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        permissionRepository.deleteById(name);

    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}
