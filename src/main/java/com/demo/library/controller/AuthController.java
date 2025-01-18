package com.demo.library.controller;

import com.demo.library.model.LibraryUser;
import com.demo.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
    public String login(@RequestBody String requestBody) throws UnsupportedEncodingException {
        String email = java.net.URLDecoder.decode(requestBody.split("&")[0].split("=")[1], "UTF-8");
        String password = java.net.URLDecoder.decode(requestBody.split("&")[1].split("=")[1], "UTF-8");
        LibraryUser existingUser = userService.login(email,password).get();
        if (existingUser != null) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }
}
