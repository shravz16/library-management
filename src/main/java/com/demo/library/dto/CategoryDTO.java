package com.demo.library.dto;

import com.demo.library.model.Category;

public class CategoryDTO {
    private Long id;
    private String name;
    private int bookCount;  // Instead of returning the full Set<Book>

    // Default constructor
    public CategoryDTO() {
    }

    // Constructor with fields
    public CategoryDTO(Long id, String name, int bookCount) {
        this.id = id;
        this.name = name;
        this.bookCount = bookCount;
    }

    // Static factory method to create from Category entity
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getBooks() != null ? category.getBooks().size() : 0
        );
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }
}