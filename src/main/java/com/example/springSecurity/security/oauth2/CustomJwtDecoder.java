package com.example.springSecurity.security.oauth2;

import com.example.springSecurity.exception.ErrorCode;
import com.example.springSecurity.security.jwt.JwtUtils;
import com.example.springSecurity.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

@Component
@Slf4j
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtUtils jwtUtils;



    @Override
    public Jwt decode(String token) throws JwtException {
        SecretKeySpec spec=new SecretKeySpec(SECRET_KEY.getBytes(),"HS512");
        String jwtId= jwtUtils.getIdJwt(token);
        if(jwtId!=null&&redisService.get(jwtId)!=null){
            log.error(ErrorCode.LOGGED_OUT.getMessage());
            return NimbusJwtDecoder.
                    withSecretKey(spec)
                    .macAlgorithm(MacAlgorithm.HS256).build().decode(null);
        }
        return NimbusJwtDecoder.
                withSecretKey(spec)
                .macAlgorithm(MacAlgorithm.HS256).build().decode(token);
    }
}
