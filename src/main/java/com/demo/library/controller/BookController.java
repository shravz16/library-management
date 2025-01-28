package com.demo.library.controller;

import com.demo.library.dto.BookDTO;
import com.demo.library.dto.CategoryDTO;
import com.demo.library.model.Book;
import com.demo.library.model.Category;
import com.demo.library.model.CheckoutHistory;
import com.demo.library.service.BookService;
import com.demo.library.service.CategoryService;
import com.demo.library.service.CheckoutHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CheckoutHistoryService checkoutHistory;

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookService.findAll().stream() .map(BookDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @PostMapping("/add")
    public String createBook(@RequestBody BookDTO bookDTO,@RequestHeader String Username) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());

        // Get category from repository and set it
        Category category = categoryService.findById(bookDTO.getCategoryId());
        book.setCategory(category);

        Book savedBook = bookService.save(book);
        //bookService.save(book);

        return "Book added successfully!";
    }


    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Long id) {

        return BookDTO.fromEntity(bookService.findById(id).get());
    }



    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody String book,@RequestHeader String Username) {System.out.println(book);
        Book bookObj = bookService.findById(id).get();
        bookObj.setCheckedOut(true);
        bookService.save(bookObj);
        CheckoutHistory checkoutHistory1=new CheckoutHistory();
        checkoutHistory1.setUsername(Username);
        checkoutHistory1.setBookId(bookObj.getId());
        checkoutHistory1.setCheckoutDate(LocalDateTime.now());
        checkoutHistory1.setReturnDate(LocalDateTime.now().plusDays(30));
        checkoutHistory.save(checkoutHistory1);
        return "Book updated successfully!";
    }

    // Delete a book by ID
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "Book deleted successfully!";
    }
}
