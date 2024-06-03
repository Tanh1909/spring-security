package com.example.springSecurity.service.serviceImpl;

import com.example.springSecurity.entity.User;
import com.example.springSecurity.exception.AppException;
import com.example.springSecurity.exception.ErrorCode;
import com.example.springSecurity.security.jwt.JwtUtils;
import com.example.springSecurity.repository.UserRepository;
import com.example.springSecurity.service.AuthService;
import com.example.springSecurity.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisService redisService;
    @Override
    public String login(User reqUser) {
        User user=userRepository.findByUsername(reqUser.getUsername()).orElseThrow(() -> new AppException(ErrorCode.WRONG_USERNAME_PASSWORD));
        if(passwordEncoder.matches(reqUser.getPassword(),user.getPassword())){
            return jwtUtils.generateToken(user);
        }
        else {
            throw new AppException(ErrorCode.WRONG_USERNAME_PASSWORD);
        }
    }

    @Override
    public User register(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if(user.getRole()==null||user.getRole().isBlank()){
//            user.setRole(Role.USER.name());
//        }
        return userRepository.save(user);
    }

    @Override
    public void logout(String token) {
        if(token!=null&&token.startsWith("Bearer ")){
            String jwtToken=token.substring(7);
            String id=jwtUtils.getIdJwt(jwtToken);
            long expiration=jwtUtils.getExpiration(jwtToken);
            redisService.set(id,jwtToken,expiration);
        }
        else {
            throw new AppException(ErrorCode.WRONG_USERNAME_PASSWORD);
        }
    }
}
