package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.AuthenticationRequest;
import com.example.bookstoreapi.model.AuthenticationResponse;
import com.example.bookstoreapi.security.JwtUtil;
import com.example.bookstoreapi.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request) {
        userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthenticationResponse(token);
    }
}