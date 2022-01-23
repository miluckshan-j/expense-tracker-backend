package com.example.demo.transactions;

import com.example.demo.categories.Category;
import com.example.demo.categories.CategoryRepository;
import com.example.demo.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/transactions")
    public ResponseEntity getTransactions() {
        try {
            Iterable<Transaction> transactions = transactionRepository.findAll();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity findTransactionById(@PathVariable Integer id) {
        try {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity deleteTransactionById(@PathVariable Integer id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity addTransaction(@RequestBody TransactionDTO transactionDTO) {
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
                return new ResponseEntity<>(transaction, HttpStatus.CREATED);
            } else {
//                Period -> 1, 2, 3, ... n
//                Frequency -> days, weeks, months
//                End Date -> YYYY-MM-DD
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
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void saveTransaction(TransactionDTO transactionDTO, LocalDate d) {
        Transaction transaction = new Transaction();
        transaction.setNote(transactionDTO.getNote());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());

        Category category = categoryRepository.findCategoryById(transactionDTO.getCategoryId());
        transaction.setCategory(category);
        transaction.setIsRecurring(true);
        transaction.setDate(d);
        transactionRepository.save(transaction);
    }

    @PutMapping("/transactions/{id}")
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
