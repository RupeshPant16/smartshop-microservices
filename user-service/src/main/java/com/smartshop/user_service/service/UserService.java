package com.smartshop.user_service.service;

import com.smartshop.user_service.entity.User;

import java.util.Optional;

public interface UserService {
    String signUp(User user);
    String login(String email, String password);
    Optional<User> getUserByEmail(String email);
    Optional<User> getCurrentUser(String token);
}
