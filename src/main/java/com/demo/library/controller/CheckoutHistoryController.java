package com.demo.library.controller;

import com.demo.library.model.CheckoutHistory;
import com.demo.library.service.CheckoutHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/checkout-history")
public class CheckoutHistoryController {

    @Autowired
    private CheckoutHistoryService checkoutHistoryService;

    // Record a new checkout history
    @PostMapping
    public String createCheckoutHistory(@RequestBody CheckoutHistory history) {
        checkoutHistoryService.save(history);
        return "Checkout history recorded successfully!";
    }

    // Get all checkout histories
    @GetMapping
    public Map<Long, CheckoutHistory> getCheckoutHistories() {
        return checkoutHistoryService.findAll();
    }

    // Delete a checkout history by ID
    @DeleteMapping("/{id}")
    public String deleteCheckoutHistory(@PathVariable Long id) {
        checkoutHistoryService.delete(id);
        return "Checkout history deleted successfully!";
    }
}
