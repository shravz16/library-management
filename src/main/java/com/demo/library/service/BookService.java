package com.demo.library.service;

import com.demo.library.model.Book;
import com.demo.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Save or update a book
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Find a book by ID
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    // Get all books
    public Map<Long, Book> findAll() {
        return bookRepository.findAll();
    }

    // Delete a book by ID
    public void delete(Long id) {
        bookRepository.delete(id);
    }
}
