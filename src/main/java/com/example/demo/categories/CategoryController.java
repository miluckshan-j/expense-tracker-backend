package com.example.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/categories")
    public String addCategory( @RequestParam String name, @RequestParam String logo) {
        Category category = new Category();
        category.setName(name);
        category.setLogo(logo);
        category.setDate(LocalDateTime.now());
        categoryRepository.save(category);
        return "Added new category to repo!";
    }

    @GetMapping("/categories/")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category findCategoryById(@PathVariable Integer id) {
        return categoryRepository.findCategoryById(id);
    }
}
