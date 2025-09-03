package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.LoginRequest;
import org.example.smarthealthcareappointmentsystem.model.User;
import org.example.smarthealthcareappointmentsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        return ResponseEntity.ok(this.authService.register(user));
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){


        return ResponseEntity.ok( this.authService.verify(loginRequest));
    }



}
