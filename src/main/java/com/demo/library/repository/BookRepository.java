package com.demo.library.repository;

import com.demo.library.model.Book;
import com.demo.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by author (case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Find books by category
    List<Book> findByCategory(Category category);

    // Find books by checkout status
    List<Book> findByCheckedOut(boolean checkedOut);

    // Find books by title and author
    Book findByTitleAndAuthor(String title, String author);

    // Custom query to find available books by category
    @Query("SELECT b FROM Book b WHERE b.category = :category AND b.checkedOut = false")
    List<Book> findAvailableBooksByCategory(@Param("category") Category category);

    // Custom query to count books by category
    @Query("SELECT COUNT(b) FROM Book b WHERE b.category = :category")
    long countBooksByCategory(@Param("category") Category category);

    // Count books by author
    long countByAuthor(String author);

    // Check if a book exists by title and author
    boolean existsByTitleAndAuthor(String title, String author);

    // Find all books ordered by title
    List<Book> findAllByOrderByTitleAsc();
}