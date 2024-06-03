package com.example.springSecurity.security.jwt;

import com.example.springSecurity.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION;
    public  String generateToken(User user){
        Map<String,Object> claims=new HashMap<>();
        claims.put("scope",getScope(user));
        Date now=new Date();
        Date expiration=new Date(now.getTime()+JWT_EXPIRATION);
        try{
            String token= Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256,SECRET_KEY.getBytes())
                    .setId(UUID.randomUUID().toString())
                    .addClaims(claims)
                    .compact();
            return token;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
    public String getIdJwt(String token){
        Claims claims=getBody(token);
        if(claims!=null){
            return claims.getId();
        }
        return null;
    }
    public Claims getBody(String token){
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
    public Long getExpiration(String token){
        Claims claims=getBody(token);
        if(claims!=null){
            long dateIssue=claims.getIssuedAt().getTime();
            long dateExpiration=claims.getExpiration().getTime();
            return dateExpiration-dateIssue;
        }
        return null;
    }
    public String getScope(User user){
        StringJoiner stringJoiner=new StringJoiner(" ");
         user.getRoles().stream()
                .forEach(role -> {
                    stringJoiner.add("ROLE_"+role.getName());
                    role.getPermissions().stream()
                            .forEach(permission -> {
                                stringJoiner.add(permission.getName());
                            });
                });
         return stringJoiner.toString();
    }
}
