package com.demo.library.controller;

import com.demo.library.model.LibraryUser;
import com.demo.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public String register(@RequestBody LibraryUser user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    // Login a user
    @PostMapping("/login")
    public String login(@RequestBody String username, @RequestBody String password) {
        System.out.println(username+" "+password);
        LibraryUser existingUser = userService.login(username,password).get();
        if (existingUser != null) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }
}
