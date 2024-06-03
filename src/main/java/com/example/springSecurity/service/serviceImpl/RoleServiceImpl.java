package com.example.springSecurity.service.serviceImpl;

import com.example.springSecurity.dto.request.RoleCreateRequest;
import com.example.springSecurity.entity.Permission;
import com.example.springSecurity.entity.Role;
import com.example.springSecurity.exception.AppException;
import com.example.springSecurity.exception.ErrorCode;
import com.example.springSecurity.mapper.RoleMapper;
import com.example.springSecurity.repository.PermissionRepository;
import com.example.springSecurity.repository.RoleRepository;
import com.example.springSecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Role create(RoleCreateRequest roleCreateRequest) {
        Role role=roleMapper.toRole(roleCreateRequest);
        if(!CollectionUtils.isEmpty(roleCreateRequest.getPermissions())){
            Set<Permission> permissions=roleCreateRequest.getPermissions()
                    .stream()
                    .map(name ->permissionRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND)))
                    .collect(Collectors.toSet());
            role.setPermissions(permissions);
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(String name) {
        Role role=roleRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        roleRepository.deleteById(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
