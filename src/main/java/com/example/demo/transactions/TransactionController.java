package com.example.demo.transactions;

import com.example.demo.categories.Category;
import com.example.demo.categories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/transactions")
    public ResponseEntity addTransaction(@RequestParam double amount, @RequestParam String type, @RequestParam Integer categoryId) {
        try {
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setType(type);
            transaction.setDate(LocalDateTime.now());
//            If note exists, add that
//            If recurring, add multiple saves or the code below
            Category category = categoryRepository.findCategoryById(categoryId);
            transaction.setCategory(category);
            transactionRepository.save(transaction);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity getTransactions() {
        try {
            Iterable<Transaction> transactions = transactionRepository.findAll();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions/find/{id}")
    public ResponseEntity findTransactionById(@PathVariable Integer id) {
        try {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions/delete/{id}")
    public ResponseEntity deleteTransactionById(@PathVariable Integer id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/transactions/edit/{id}")
    public ResponseEntity editTransactionById(@PathVariable Integer id, @RequestParam double amount) {
        try {
            Transaction transaction = transactionRepository.findTransactionById(id);
            transaction.setAmount(amount);
            transactionRepository.save(transaction);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
