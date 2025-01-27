package com.demo.library.dto;

import com.demo.library.model.LibraryUser;

public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private boolean active;
    private int booksBorrowed;
    private String password;
    public UserDTO() {
    }

    public static UserDTO fromEntity(LibraryUser user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        dto.setPassword(user.getPassword());
        return dto;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public int getBooksBorrowed() { return booksBorrowed; }
    public void setBooksBorrowed(int booksBorrowed) { this.booksBorrowed = booksBorrowed; }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", booksBorrowed=" + booksBorrowed +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

}