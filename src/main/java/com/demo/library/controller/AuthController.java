package com.demo.library.controller;


import com.demo.library.dto.UserDTO;
import com.demo.library.model.LibraryUser;
import com.demo.library.service.CheckoutHistoryService;
import com.demo.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckoutHistoryService checkoutHistoryService;
    // Register a new user
    @PostMapping("/register")
    public UserDTO register(@RequestBody LibraryUser user) {
        LibraryUser reg=userService.registerUser(user);
        return UserDTO.fromEntity(reg);

    }

    // Login a user
    @PostMapping("/login")
    public UserDTO login(@RequestBody String requestBody) throws UnsupportedEncodingException {
        String email = java.net.URLDecoder.decode(requestBody.split("&")[0].split("=")[1], "UTF-8");
        String password = java.net.URLDecoder.decode(requestBody.split("&")[1].split("=")[1], "UTF-8");
        LibraryUser existingUser = userService.login(email,password).get();
        if (existingUser != null) {
            return  UserDTO.fromEntity(existingUser);
        }
        return null;
    }
    @GetMapping("/users/{username}")
    public UserDTO getUser(@PathVariable String username) {
         LibraryUser libraryUser= userService.findByUsername(username);
         UserDTO dto=UserDTO.fromEntity(libraryUser);
         dto.setBooksBorrowed(checkoutHistoryService.findByUserName(username).size());
         return dto;
    }

    @PutMapping("/users/toggle-status/{id}")
    public String getUser(@PathVariable Long id) {
        LibraryUser libraryUser= userService.findById(id).get();
        libraryUser.setActive(!libraryUser.isActive());
        userService.updateUser(libraryUser);
        return "successfully toggled";
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "successful delete";
    }

}
