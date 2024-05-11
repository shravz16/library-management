package com.demo.library.service;

import com.demo.library.model.User;
import com.demo.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;




    // Method to register a new user
    public void register(User user) {
        user.setPassword(user.getPassword()); // Encrypt the password
        userRepository.save(user); // Save the user to the repository
    }

    // Method to find a user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // Retrieve the user from the repository
    }
}
