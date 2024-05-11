package com.demo.library.repository;

import com.demo.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {
    private Map<Long, Book> books = new HashMap<>();
    private Long currentId = 1L;

    // Save or update a book
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(currentId++);
        }
        books.put(book.getId(), book);
        return book;
    }

    // Find a book by ID
    public Book findById(Long id) {
        return books.get(id);
    }

    // Get all books
    public Map<Long, Book> findAll() {
        return books;
    }

    // Delete a book by ID
    public void delete(Long id) {
        books.remove(id);
    }
}
