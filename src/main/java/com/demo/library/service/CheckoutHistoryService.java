package com.demo.library.service;

import com.demo.library.model.CheckoutHistory;
import com.demo.library.repository.CheckoutHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckoutHistoryService {

    @Autowired
    private CheckoutHistoryRepository checkoutHistoryRepository;

    // Save or update a checkout history
    public CheckoutHistory save(CheckoutHistory history) {
        return checkoutHistoryRepository.save(history);
    }

    // Get all checkout histories
    public Map<Long, CheckoutHistory> findAll() {
        return checkoutHistoryRepository.findAll();
    }

    // Delete a checkout history by ID
    public void delete(Long id) {
        checkoutHistoryRepository.delete(id);
    }
}
