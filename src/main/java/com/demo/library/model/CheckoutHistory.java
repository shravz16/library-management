package com.demo.library.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class CheckoutHistory {
    private Long id;
    private String username;
    private Long bookId;
    private LocalDateTime checkoutDate;
    private LocalDateTime returnDate;

    public CheckoutHistory(Long id, String username, Long bookId, LocalDateTime checkoutDate, LocalDateTime returnDate) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
