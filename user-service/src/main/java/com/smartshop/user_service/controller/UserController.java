package com.smartshop.user_service.controller;

import com.smartshop.user_service.entity.User;
import com.smartshop.user_service.service.AuthenticationService;
import com.smartshop.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    SignUp the user for first time.
    Returns successful signup message.
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        return ResponseEntity.ok(userService.signUp(user));
    }

    /*
    Login a User if its present in DB.
    Return an authentication token.
     */
    @PostMapping("/login")
    public ResponseEntity<String> signUp(@RequestParam String email, @RequestParam  String password) {
        String token = userService.login(email, password);
        return ResponseEntity.ok(token);
    }

    /*
    Returns the detail of user based on authentication header.
     */
    @GetMapping("/users/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Optional<User> user= userService.getCurrentUser(token);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
