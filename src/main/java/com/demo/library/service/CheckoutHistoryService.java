package com.demo.library.service;

import com.demo.library.model.CheckoutHistory;
import com.demo.library.repository.CheckoutHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CheckoutHistoryService {

    @Autowired
    private CheckoutHistoryRepository checkoutHistoryRepository;

    // Save or update a checkout history
    public CheckoutHistory save(CheckoutHistory history) {
        return checkoutHistoryRepository.save(history);
    }
    public List<CheckoutHistory> findByUserName(String name) {
        return checkoutHistoryRepository.findByUsername(name);
    }
    public CheckoutHistory findByBookId(Long id) {
        return checkoutHistoryRepository.findByBookId(id).get(0);
    }
    // Get all checkout histories
    public List<CheckoutHistory> findAll() {
        return checkoutHistoryRepository.findAll();
    }

    // Delete a checkout history by ID
    public void delete(Long id) {
        checkoutHistoryRepository.deleteById(id);
    }

    @Transactional
    public void deleteByBookId(Long id) {
        checkoutHistoryRepository.deleteByBookId(id);
    }

    public CheckoutHistory findById(Long id){
        return checkoutHistoryRepository.findById(id).get();
    }
}
