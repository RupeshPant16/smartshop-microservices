package com.smartshop.user_service.service;

public interface AuthenticationService
{
    String generateToken(String token);
    boolean validateToken(String token);
    String extractToken(String token);
}
