package com.example.demo.transactions;

import com.example.demo.categories.Category;
import com.example.demo.categories.CategoryRepository;
import com.example.demo.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<Transaction> findTransactionById(Integer id) {
        try {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            return transaction;
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<Transaction> getTransactions(Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        try {
            Iterable<Transaction> transactions = null;
            if (startDate.isEmpty()) {
                transactions = transactionRepository.getTransactions();
            } else {
                transactions = transactionRepository.getTransactionsBetweenDateRange(startDate.get(), endDate.get());
            }
            return transactions;
        } catch (Exception e) {
            return null;
        }
    }

    public Transaction deleteTransactionById(Integer id) {
        try {
            Transaction transaction = new Transaction();
            transactionRepository.deleteById(id);
            return transaction;
        } catch (Exception e) {
            return null;
        }
    }

    public Transaction addTransaction(TransactionDTO transactionDTO) {
        try {
            if (transactionDTO.getPeriod() == null) {
                Transaction transaction = new Transaction();
                transaction.setNote(transactionDTO.getNote());
                transaction.setAmount(transactionDTO.getAmount());
                transaction.setType(transactionDTO.getType());
                transaction.setDate(transactionDTO.getDate());

                Category category = categoryRepository.findCategoryById(transactionDTO.getCategoryId());
                transaction.setCategory(category);
                transactionRepository.save(transaction);
                return transaction;
            } else {
                Transaction transaction = new Transaction();
                switch (transactionDTO.getFrequency()) {
                    case "months":
                        for (LocalDate d = transactionDTO.getDate(); d.isBefore(transactionDTO.getEndDate()); d = d.plusMonths(transactionDTO.getPeriod())) {
                            saveTransaction(transactionDTO, d);
                        }
                        break;
                    case "weeks":
                        for (LocalDate d = transactionDTO.getDate(); d.isBefore(transactionDTO.getEndDate()); d = d.plusWeeks(transactionDTO.getPeriod())) {
                            saveTransaction(transactionDTO, d);
                        }
                        break;
                    default:
                        for (LocalDate d = transactionDTO.getDate(); d.isBefore(transactionDTO.getEndDate()); d = d.plusDays(transactionDTO.getPeriod())) {
                            saveTransaction(transactionDTO, d);
                        }
                }
                return transaction;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //    TODO: Put in helpers
    private Transaction saveTransaction(TransactionDTO transactionDTO, LocalDate d) {
        Transaction transaction = new Transaction();
        transaction.setNote(transactionDTO.getNote());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());

        Category category = categoryRepository.findCategoryById(transactionDTO.getCategoryId());
        transaction.setCategory(category);
        transaction.setIsRecurring(true);
        transaction.setDate(d);
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction editTransactionById(Integer id, Transaction updatedTransaction) {
        try {
            Transaction transaction = transactionRepository.findTransactionById(id);
            transaction.setNote(updatedTransaction.getNote());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setType(updatedTransaction.getType());
            transaction.setIsRecurring(updatedTransaction.isRecurring());
            transaction.setDate(updatedTransaction.getDate());
            transactionRepository.save(transaction);
            return transaction;
        } catch (Exception e) {
            return null;
        }
    }
}
