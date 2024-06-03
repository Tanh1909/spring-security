package com.example.springSecurity.mapper;

import com.example.springSecurity.dto.request.RoleCreateRequest;
import com.example.springSecurity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleCreateRequest roleCreateRequest);
}
