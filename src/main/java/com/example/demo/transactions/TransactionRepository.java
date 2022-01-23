package com.example.demo.transactions;

import com.example.demo.utils.interfaces.ICategoryTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTransactionById(Integer id);

    void deleteById(Integer id);

    @Query(value = "SELECT t.category AS category, SUM(t.amount) AS total FROM Transaction t WHERE t.type= :type AND t.date >= :startDate AND t.date<= :endDate GROUP BY t.category")
    List<ICategoryTotal> getTransactionSpentInCategories(@Param("type") String type, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT t FROM Transaction t ORDER BY t.date ASC")
    List<Transaction> getTransactions();

    @Query(value = "SELECT t FROM Transaction t WHERE t.date >= :startDate AND t.date<= :endDate ORDER BY t.date ASC")
    List<Transaction> getTransactionsBetweenDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
