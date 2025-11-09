package com.smartshop.user_service.service;

import com.smartshop.user_service.entity.User;
import com.smartshop.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public String signUp(User user) {
        userRepository.save(user);
        return "Successfully signed up!!";
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (password != null && !password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return authenticationService.generateToken(user.getEmail());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getCurrentUser(String token) {
        String email = authenticationService.extractToken(token);
        return getUserByEmail(email);
    }
}
