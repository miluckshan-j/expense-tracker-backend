package com.example.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class TransactionDTO {
    String note;

    Double amount;

    String type;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;

    Integer categoryId;

    Integer period;

    String frequency;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;

    public String getNote() {
        return note;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getPeriod() {
        return period;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
