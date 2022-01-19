package com.example.demo.transactions;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    Transaction findTransactionById(Integer id);

    void deleteById(Integer id);

}
