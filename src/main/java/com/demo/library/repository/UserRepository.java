package com.demo.library.repository;

import com.demo.library.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Long> {

    // Existing methods
    LibraryUser findByUsername(String username);
    List<LibraryUser> findByPassword(String password);
    boolean existsByUsername(String username);
    Optional<LibraryUser> findByEmail(String email);
    List<LibraryUser> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
    List<LibraryUser> findAllByOrderByUsernameAsc();
    List<LibraryUser> findByActive(boolean active);
    void deleteByUsername(String username);

    // Method for login (find by email only, password check will be in service)
    Optional<LibraryUser> findByEmailAndActive(String email, boolean active);
}