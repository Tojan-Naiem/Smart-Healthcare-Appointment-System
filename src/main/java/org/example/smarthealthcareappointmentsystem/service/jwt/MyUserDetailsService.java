package org.example.smarthealthcareappointmentsystem.service.jwt;

import org.example.smarthealthcareappointmentsystem.entity.User;
import org.example.smarthealthcareappointmentsystem.entity.UserPrincipal;
import org.example.smarthealthcareappointmentsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        User user=userOptional.get();

        return new UserPrincipal(user);
    }
}
