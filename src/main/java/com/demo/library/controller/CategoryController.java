package com.demo.library.controller;

import com.demo.library.dto.CategoryDTO;
import com.demo.library.model.Category;
import com.demo.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Create a new category
    @PostMapping("/add")
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
