package com.example.demo.transactions;

import com.example.demo.categories.Category;
import com.example.demo.categories.CategoryRepository;
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
    public ResponseEntity addTransaction(@RequestParam("note") Optional<String> note, @RequestParam double amount, @RequestParam String type, @RequestParam(value = "date", defaultValue = "2022-10-12") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam Integer categoryId, @RequestParam(value = "period") Optional<Integer> period, @RequestParam(value = "frequency", defaultValue = "days") String frequency, @RequestParam(value = "endDate", defaultValue = "2022-12-12") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            if (period.isEmpty()) {
                Transaction transaction = new Transaction();
                note.ifPresent(transaction::setNote);
                transaction.setAmount(amount);
                transaction.setType(type);
                transaction.setDate(date);

                Category category = categoryRepository.findCategoryById(categoryId);
                transaction.setCategory(category);
                transactionRepository.save(transaction);
                return new ResponseEntity<>(transaction, HttpStatus.CREATED);
            } else {
//                Period -> 1, 2, 3, ... n
//                Frequency -> days, weeks, months
//                End Date -> YYYY-MM-DD
                switch (frequency) {
                    case "months":
                        for (LocalDate d = date; d.isBefore(endDate); d = d.plusMonths(period.get())) {
                            Transaction transaction = new Transaction();
                            note.ifPresent(transaction::setNote);
                            transaction.setAmount(amount);
                            transaction.setType(type);
                            transaction.setDate(date);

                            Category category = categoryRepository.findCategoryById(categoryId);
                            transaction.setCategory(category);
                            transaction.setIsRecurring(true);
                            transaction.setDate(d);
                            transactionRepository.save(transaction);
                        }
                        break;
                    case "weeks":
                        for (LocalDate d = date; d.isBefore(endDate); d = d.plusWeeks(period.get())) {
                            Transaction transaction = new Transaction();
                            note.ifPresent(transaction::setNote);
                            transaction.setAmount(amount);
                            transaction.setType(type);
                            transaction.setDate(date);

                            Category category = categoryRepository.findCategoryById(categoryId);
                            transaction.setCategory(category);
                            transaction.setIsRecurring(true);
                            transaction.setDate(d);
                            transactionRepository.save(transaction);
                        }
                        break;
                    default:
                        for (LocalDate d = date; d.isBefore(endDate); d = d.plusDays(period.get())) {
                            Transaction transaction = new Transaction();
                            note.ifPresent(transaction::setNote);
                            transaction.setAmount(amount);
                            transaction.setType(type);
                            transaction.setDate(date);

                            Category category = categoryRepository.findCategoryById(categoryId);
                            transaction.setCategory(category);
                            transaction.setIsRecurring(true);
                            transaction.setDate(d);
                            transactionRepository.save(transaction);
                        }
                }
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
