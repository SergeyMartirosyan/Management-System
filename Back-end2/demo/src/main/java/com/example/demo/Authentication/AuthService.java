package com.example.demo.Authentication;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.LighterConfig.LoginRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.SECRET}")
    private String SECRET;

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days


    public AuthResponse authenticate(LoginRequest request){
        if (username.equals(request.getUsername()) && password.equals(request.getPassword())){
            return new AuthResponse(generateToken(username));
        }
        else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    private String generateToken(String username){
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }
}
