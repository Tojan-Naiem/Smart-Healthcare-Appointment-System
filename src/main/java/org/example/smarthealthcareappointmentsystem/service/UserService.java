package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.LoginRequest;
import org.example.smarthealthcareappointmentsystem.entity.User;

public interface UserService {
    public User register(User user);
    public String verify(LoginRequest loginRequest);
}
