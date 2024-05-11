package com.demo.library.repository;

import com.demo.library.model.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryRepository {
    private Map<Long, Category> categories = new HashMap<>();
    private Long currentId = 1L;

    // Save or update a category
    public Category save(Category category) {
        if (category.getId() == null) {
            category.setId(currentId++);
        }
        categories.put(category.getId(), category);
        return category;
    }

    // Get a category by ID
    public Category findById(Long id) {
        return categories.get(id);
    }

    // Get all categories
    public Map<Long, Category> findAll() {
        return categories;
    }

    // Delete a category by ID
    public void delete(Long id) {
        categories.remove(id);
    }
}
