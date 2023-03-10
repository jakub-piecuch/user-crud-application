package com.kubapiecuch.springbootwithdatabase.controller;

import com.kubapiecuch.springbootwithdatabase.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users/token")
public class AuthController {
    private final TokenService tokenService;

    @Autowired
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping()
    public ResponseEntity<String> authenticateForToken(Authentication authentication){
        String token = tokenService.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
