package com.example.demo.LighterConfig;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest( @JsonProperty("username") String username, @JsonProperty("password") String password){
        this.username = username;
        this.password = password;
    }
}
