package com.demo.library.controller;

import com.demo.library.model.Category;
import com.demo.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public Map<Long, Category> getCategories() {
        return categoryService.findAll();
    }

    // Create a new category
    @PostMapping
    public String createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return "Category added successfully!";
    }

    // Delete a category by ID
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "Category deleted successfully!";
    }
}
