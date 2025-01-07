package com.demo.library.service;

import com.demo.library.model.Category;
import com.demo.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Save or update a category
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // Get all categories
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Delete a category by ID
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
