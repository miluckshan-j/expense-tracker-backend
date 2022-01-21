package com.example.demo.progress;

import com.example.demo.utils.interfaces.ICategoryTotal;
import com.example.demo.transactions.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProgressController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/progress/transactionTotalPerCategory")
    public ResponseEntity getTransactionTotalPerCategory(@RequestParam(value = "type", defaultValue = "expense") String type, @RequestParam(value = "date", defaultValue = "1999-01-12") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(value = "endDate", defaultValue = "2040-12-12") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<ICategoryTotal> transaction = transactionRepository.getTransactionSpentInCategories(type, date, endDate);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
