package com.demo.library.repository;

import com.demo.library.model.CheckoutHistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CheckoutHistoryRepository {
    private Map<Long, CheckoutHistory> histories = new HashMap<>();
    private Long currentId = 1L;

    // Save or update a checkout history
    public CheckoutHistory save(CheckoutHistory history) {
        if (history.getId() == null) {
            history.setId(currentId++);
        }
        histories.put(history.getId(), history);
        return history;
    }

    // Get all checkout histories
    public Map<Long, CheckoutHistory> findAll() {
        return histories;
    }

    // Delete a checkout history by ID
    public void delete(Long id) {
        histories.remove(id);
    }
}
