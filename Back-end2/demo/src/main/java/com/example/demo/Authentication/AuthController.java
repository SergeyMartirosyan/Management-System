package com.example.demo.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.LighterConfig.LoginRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authservice.authenticate(request));
    }
}
