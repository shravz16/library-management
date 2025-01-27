package com.demo.library.controller;


import com.demo.library.dto.UserDTO;
import com.demo.library.model.LibraryUser;
import com.demo.library.service.CheckoutHistoryService;
import com.demo.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<?> login(@RequestBody String requestBody) throws UnsupportedEncodingException {
        try {
            // Log the incoming request
            System.out.println("Received request body: " + requestBody);

            String email = java.net.URLDecoder.decode(requestBody.split("&")[0].split("=")[1], "UTF-8");
            String password = java.net.URLDecoder.decode(requestBody.split("&")[1].split("=")[1], "UTF-8");

            System.out.println("Decoded email: " + email);
            System.out.println("Decoded password: " + password);

            Optional<LibraryUser> userOptional = userService.login(email, password);

            if (userOptional.isPresent()) {
                UserDTO userDTO = UserDTO.fromEntity(userOptional.get());
                System.out.println("Returning user: " + userDTO);
                return ResponseEntity.ok(userDTO);
            } else {
                System.out.println("User not found");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }

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
