package com.example.demo.transactions;

import com.example.demo.categories.Category;
import com.example.demo.categories.CategoryRepository;
import com.example.demo.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions/{id}")
    public ResponseEntity findTransactionById(@PathVariable Integer id) {
        Optional<Transaction> response = transactionService.findTransactionById(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity getTransactions(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate) {
        Iterable<Transaction> response = transactionService.getTransactions(startDate, endDate);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity deleteTransactionById(@PathVariable Integer id) {
        Transaction response = transactionService.deleteTransactionById(id);
        if (response != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity addTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction response = transactionService.addTransaction(transactionDTO);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity editTransactionById(@PathVariable Integer id, @RequestBody Transaction updatedTransaction) {
        Transaction response = transactionService.editTransactionById(id, updatedTransaction);
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
