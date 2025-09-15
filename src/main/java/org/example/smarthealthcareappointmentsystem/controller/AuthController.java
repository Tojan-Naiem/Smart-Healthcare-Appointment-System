package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.LoginRequest;
import org.example.smarthealthcareappointmentsystem.entity.User;
import org.example.smarthealthcareappointmentsystem.service.Impl.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling user auth operations -> login and register(for admins)
 * Provide endpoints for login and register
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserServiceImp userServiceImp;
    public AuthController(UserServiceImp userServiceImp){
        this.userServiceImp=userServiceImp;
    }

    /**
     * Creates a new user (Admin )
     * @param user user details to create
     * @return {@link  ResponseEntity} containing the user info
     */

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return new ResponseEntity<>(this.userServiceImp.register(user), HttpStatus.CREATED);
    }

    /**
     * Login a user ( Patient ,Doctor,Admin)
     * @param loginRequest have the username and the password to login
     * @return {@link String} containing the token
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(this.userServiceImp.verify(loginRequest));
    }



}
