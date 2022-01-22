package com.example.demo.categories;

import com.example.demo.transactions.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private Double budget;

    private LocalDate date;

    @OneToMany(mappedBy="category")
    @JsonIgnore
    private Set<Transaction> transactions;

    public Category(Integer id, String name, Double budget, LocalDate date) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.date = date;
    }

    public Category() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }
}
