package com.demo.library.repository;

import com.demo.library.model.CheckoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckoutHistoryRepository extends JpaRepository<CheckoutHistory, Long> {

    // Find checkouts by username
    List<CheckoutHistory> findByUsername(String username);

    // Find checkouts by book ID
    List<CheckoutHistory> findByBookId(Long bookId);

    // Find current checkouts (where returnDate is null)
    List<CheckoutHistory> findByReturnDateIsNull();

    // Find checkouts within a date range
    List<CheckoutHistory> findByCheckoutDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find by username and book ID
    List<CheckoutHistory> findByUsernameAndBookId(String username, Long bookId);

    // Find overdue checkouts (return date is null and checkout date is before specified date)
    @Query("SELECT ch FROM CheckoutHistory ch WHERE ch.returnDate IS NULL AND ch.checkoutDate < :date")
    List<CheckoutHistory> findOverdueCheckouts(@Param("date") LocalDateTime date);

    // Count checkouts by username
    long countByUsername(String username);

    // Count current checkouts
    long countByReturnDateIsNull();

    // Find most recent checkout for a book
    @Query("SELECT ch FROM CheckoutHistory ch WHERE ch.bookId = :bookId ORDER BY ch.checkoutDate DESC LIMIT 1")
    CheckoutHistory findMostRecentCheckoutForBook(@Param("bookId") Long bookId);

    // Find checkouts with return date
    List<CheckoutHistory> findByReturnDateIsNotNull();
}