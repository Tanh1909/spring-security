package com.example.springSecurity.mapper;

import com.example.springSecurity.dto.request.UserCreateRequest;
import com.example.springSecurity.dto.request.UserUpdateRequest;
import com.example.springSecurity.dto.response.UserResponse;
import com.example.springSecurity.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);
    UserResponse toUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "roles",ignore = true)
    void toUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
