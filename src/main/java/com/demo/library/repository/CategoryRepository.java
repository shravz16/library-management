package com.demo.library.repository;

import com.demo.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find category by name (case-insensitive)
    Category findByNameIgnoreCase(String name);

    // Find categories by name containing (case-insensitive)
    List<Category> findByNameContainingIgnoreCase(String namePart);

    // Check if category exists by name
    boolean existsByNameIgnoreCase(String name);

    // Find all categories ordered by name
    List<Category> findAllByOrderByNameAsc();

    // Custom query to find categories with book count
    @Query("SELECT c, COUNT(b) FROM Category c LEFT JOIN c.books b GROUP BY c")
    List<Object[]> findAllWithBookCount();

    // Custom query to find categories that have at least one book
    @Query("SELECT DISTINCT c FROM Category c JOIN c.books b")
    List<Category> findCategoriesWithBooks();

    // Custom query to find categories with more than X books
    @Query("SELECT c FROM Category c WHERE SIZE(c.books) > :count")
    List<Category> findCategoriesWithMoreThanXBooks(@Param("count") int count);

    // Find categories with no books
    @Query("SELECT c FROM Category c WHERE c.books IS EMPTY")
    List<Category> findCategoriesWithNoBooks();
}