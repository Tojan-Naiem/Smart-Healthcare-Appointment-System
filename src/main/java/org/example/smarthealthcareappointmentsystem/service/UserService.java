package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.LoginRequest;
import org.example.smarthealthcareappointmentsystem.model.User;
import org.example.smarthealthcareappointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;


    public User register(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw  new NullPointerException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

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
