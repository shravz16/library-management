package com.demo.library.controller;

import com.demo.library.dto.BookDTO;
import com.demo.library.dto.CheckoutHistoryDTO;
import com.demo.library.model.Book;
import com.demo.library.model.CheckoutHistory;
import com.demo.library.service.BookService;
import com.demo.library.service.CheckoutHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/checkout-history")
public class CheckoutHistoryController {

    @Autowired
    private CheckoutHistoryService checkoutHistoryService;
    @Autowired
    private BookService bookService;

    // Record a new checkout history
    @PostMapping
    public String createCheckoutHistory(@RequestBody CheckoutHistory history) {
        checkoutHistoryService.save(history);
        return "Checkout history recorded successfully!";
    }

    // Get all checkout histories
    @GetMapping
    public List<CheckoutHistory> getCheckoutHistories() {
        return checkoutHistoryService.findAll();
    }

    @GetMapping("/username")
    public List<CheckoutHistoryDTO> getCheckoutHistory(@RequestHeader String Username){
        List<CheckoutHistoryDTO> list=new ArrayList<>();
        List<CheckoutHistory> checkoutHistory=checkoutHistoryService.findByUserName(Username);
        for(CheckoutHistory ch:checkoutHistory){
            Book book=bookService.findById(ch.getBookId()).get();
            list.add(CheckoutHistoryDTO.fromEntity(ch,book));
        }

        return list;
    }

    @PutMapping("/{id}")
    public String updateCheckoutHistory(@PathVariable Long id,@RequestHeader String Username){

        CheckoutHistory checkoutHistory=checkoutHistoryService.findByBookId(id);
        Book book=bookService.findById(checkoutHistory.getBookId()).get();
        book.setCheckedOut(false);
        bookService.save(book);
        checkoutHistoryService.deleteByBookId(id);
        return "updated successful";
    }
    @PutMapping("/update-extend/{id}")
    public String updateCheckoutExtend(@PathVariable Long id){
        CheckoutHistory checkoutHistory=checkoutHistoryService.findByBookId(id);
        checkoutHistory.setReturnDate(checkoutHistory.getReturnDate().plusDays(30));
        checkoutHistoryService.save(checkoutHistory);
        return "updated successful";
    }

    // Delete a checkout history by ID
    @DeleteMapping("/{id}")
    public String deleteCheckoutHistory(@PathVariable Long id) {
        checkoutHistoryService.deleteByBookId(id);
        return "Checkout history deleted successfully!";
    }
}
