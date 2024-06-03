package com.example.springSecurity.config;

import com.example.springSecurity.entity.User;
import com.example.springSecurity.repository.UserRepository;
import com.example.springSecurity.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class AppInitConfig {
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            if(!userRepository.findByUsername("admin").isPresent()){
                User user=new User();
                user.setUsername("admin");
                user.setPassword("admin");
                authService.register(user);
                log.info("CREATE ADMIN!");
            }
        };
    }
}
