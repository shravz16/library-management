package com.demo.library.service;

import com.demo.library.model.LibraryUser;
import com.demo.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Login functionality
    public Optional<LibraryUser> login(String email, String password) {
        return userRepository.findByEmailAndActive(email, true)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    // Create new user
    public LibraryUser registerUser(LibraryUser user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Find user by username
    public LibraryUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by email
    public Optional<LibraryUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Check if username exists
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Check if email exists
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Find users by name (case-insensitive search)
    public List<LibraryUser> findByNameContaining(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    // Get all users ordered by username
    public List<LibraryUser> findAllOrderedByUsername() {
        return userRepository.findAllByOrderByUsernameAsc();
    }

    // Get all users
    public List<LibraryUser> findAll() {
        return userRepository.findAll();
    }

    // Find user by ID
    public Optional<LibraryUser> findById(Long id) {
        return userRepository.findById(id);
    }

    // Find users by active status
    public List<LibraryUser> findByActive(boolean active) {
        return userRepository.findByActive(active);
    }

    // Update user
    public LibraryUser updateUser(LibraryUser user) {
        if (user.getId() == null) {
            throw new RuntimeException("Cannot update user without ID");
        }

        Optional<LibraryUser> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // If password is provided, encode it
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // Keep the existing password if not provided
            user.setPassword(existingUser.get().getPassword());
        }

        return userRepository.save(user);
    }

    // Delete user by username
    @Transactional
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    // Delete user by ID
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Deactivate user
    @Transactional
    public void deactivateUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(false);
            userRepository.save(user);
        });
    }

    // Activate user
    @Transactional
    public void activateUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(true);
            userRepository.save(user);
        });
    }

    // Change password
    @Transactional
    public void changePassword(Long id, String oldPassword, String newPassword) {
        userRepository.findById(id).ifPresent(user -> {
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new RuntimeException("Current password is incorrect");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        });
    }
}