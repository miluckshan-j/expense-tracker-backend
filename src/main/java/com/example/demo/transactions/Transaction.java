package com.example.demo.transactions;

import com.example.demo.categories.Category;

import javax.persistence.*;
import java.time.LocalDate;

@Entity()
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String note;

    private double amount;

    private String type;

    private boolean isRecurring;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Category category;

    public Transaction(Integer id, String note, double amount, String type, boolean isRecurring, LocalDate date, Category category) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.type = type;
        this.isRecurring = isRecurring;
        this.date = date;
        this.category = category;
    }

    public Transaction() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
