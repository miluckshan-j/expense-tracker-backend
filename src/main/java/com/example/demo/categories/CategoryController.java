package com.example.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/categories")
    public ResponseEntity addCategory(@RequestBody Category category) {
        try {
            category.setDate(LocalDate.now());
            System.out.println(category.getId());
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity getCategories() {
        try {
            Iterable<Category> categories = categoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity findCategoryById(@PathVariable Integer id) {
        try {
            Optional<Category> categories = categoryRepository.findById(id);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity updateCategoryById(@PathVariable Integer id, @RequestBody Category updatedCategory) {
        try {
            Category category = categoryRepository.findCategoryById(id);
            category.setName(updatedCategory.getName());
            category.setBudget(updatedCategory.getBudget());
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/categories/{id}/budget")
    public ResponseEntity addBudgetByCategoryId(@PathVariable Integer id, @RequestParam double amount) {
        try {
            Category category = categoryRepository.findCategoryById(id);
            category.setBudget(amount);
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
