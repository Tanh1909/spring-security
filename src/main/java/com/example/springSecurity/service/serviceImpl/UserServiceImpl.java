package com.example.springSecurity.service.serviceImpl;

import com.example.springSecurity.dto.request.UserUpdateRequest;
import com.example.springSecurity.entity.Role;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.exception.AppException;
import com.example.springSecurity.exception.ErrorCode;
import com.example.springSecurity.mapper.UserMapper;
import com.example.springSecurity.repository.RoleRepository;
import com.example.springSecurity.repository.UserRepository;
import com.example.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
    }

    @Override
    public User patchUpdate(Long id,UserUpdateRequest reqUser) {
        User user=findById(id);
        userMapper.toUser(user,reqUser);
        if(!CollectionUtils.isEmpty(reqUser.getRoles())){
            Set<Role> roles=reqUser.getRoles()
                    .stream().map(s -> {
                        return  roleRepository.findById(s).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
                    })
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
