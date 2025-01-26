package com.demo.library.dto;

import com.demo.library.model.Book;
import java.time.LocalDateTime;

public class CheckoutHistoryDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String username;
    private String bookTitle;
    private LocalDateTime checkoutDate;
    private LocalDateTime returnDate;

    public boolean isIscheckedout() {
        return ischeckedout;
    }

    public void setIscheckedout(boolean ischeckedout) {
        this.ischeckedout = ischeckedout;
    }

    private boolean ischeckedout;
    public CheckoutHistoryDTO() {
    }

    public CheckoutHistoryDTO(Long id,String username, String bookTitle,
                              LocalDateTime checkoutDate, LocalDateTime returnDate, boolean ischeckedout) {
        this.username = username;
        this.bookTitle = bookTitle;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.ischeckedout=ischeckedout;
        this.id=id;
    }

    public static CheckoutHistoryDTO fromEntity(com.demo.library.model.CheckoutHistory entity, Book book) {
        return new CheckoutHistoryDTO(entity.getBookId(),
                entity.getUsername(),
                book != null ? book.getTitle() : null,
                entity.getCheckoutDate(),
                entity.getReturnDate(),
                book.isCheckedOut()
        );
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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