package com.demo.library.controller;

import com.demo.library.model.Book;
import com.demo.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public Map<Long, Book> getBooks() {
        return bookService.findAll();
    }


    @PostMapping
    public String createBook(@RequestBody Book book) {
        bookService.save(book);
        return "Book added successfully!";
    }


    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.save(book);
        return "Book updated successfully!";
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "Book deleted successfully!";
    }
}
