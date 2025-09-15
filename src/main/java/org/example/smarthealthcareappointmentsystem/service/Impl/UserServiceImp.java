package org.example.smarthealthcareappointmentsystem.service.Impl;

import org.example.smarthealthcareappointmentsystem.dto.LoginRequest;
import org.example.smarthealthcareappointmentsystem.entity.User;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.repository.UserRepository;
import org.example.smarthealthcareappointmentsystem.service.UserService;
import org.example.smarthealthcareappointmentsystem.service.jwt.JWTService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link UserService} interface
 * Provides business logic for managing {@link User}entity
 */
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    public UserServiceImp(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authManager,
                          JWTService jwtService)
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authManager=authManager;
        this.jwtService=jwtService;
    }

    /**
     * Register a {@link User}
     * If the email is already exists , it will throw an exception
     * If the username is already exists , it will throw an exception
     * @param user details
     * @return the user info that saved in the database with encode password
     */
    public User register(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw  new AlreadyExistsException("Email already exists");
        }
        if(userRepository.findByUsername(user.getUsername()).isEmpty()){
            throw new AlreadyExistsException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Login a user(patient,doctor,admin) by {@link LoginRequest}
     * check if the user exists in the db and generate token
     * @param loginRequest details that contains the username and the password
     * @return the token
     */

    public String verify(LoginRequest loginRequest){
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            if (authentication.isAuthenticated())
                return jwtService.generateToken(loginRequest.getUsername());
            return "fail";
        }
       catch (BadCredentialsException e) {
        System.err.println("Authentication failed: Bad credentials - " + e.getMessage());
        return "fail: invalid email or password";
    }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("hjh");
            return "fail";
        }

    }



}
