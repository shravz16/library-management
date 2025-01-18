package com.demo.library.dto;

import com.demo.library.model.Book;


public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Long categoryId;
    private boolean checkedOut;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;
    // Default constructor
    public BookDTO() {
    }

    public static BookDTO fromEntity(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getName(),
                book.isCheckedOut()
        );
    }
    // Constructor with fields
    public BookDTO(Long id, String title, String author, String categoryName, boolean checkedOut) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.checkedOut = checkedOut;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    // You might also want to add a toString method for debugging
    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categoryName=" + categoryName +
                ", checkedOut=" + checkedOut +
                '}';
    }
}