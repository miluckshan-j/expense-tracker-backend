package com.example.demo.categories;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        try {
            category.setDate(LocalDate.now());
            category.setBudget(category.getBudget());
            categoryRepository.save(category);
            return category;
        } catch (Exception e) {
            return null;
        }
    }

    public Iterable<Category> getCategories() {
        try {
            Iterable<Category> categories = categoryRepository.findAll();
            return categories;
        } catch (Exception e) {
            return null;
        }
    }

    public Category getTransactionsByCategoryId(Integer id) {
        try {
            Category category = categoryRepository.findCategoryById(id);
            return category;
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<Category> findCategoryById(Integer id) {
        try {
            Optional<Category> categories = categoryRepository.findById(id);
            return categories;
        } catch (Exception e) {
            return null;
        }
    }

    public Category deleteCategory(Integer id) {
        try {
            Category category = new Category();
            categoryRepository.deleteById(id);
            return category;
        } catch (Exception e) {
            return null;
        }
    }

    public Category updateCategoryById(Integer id, Category updatedCategory) {
        try {
            Category category = categoryRepository.findCategoryById(id);
            if (!StringUtils.isEmpty(updatedCategory.getName())) {
                category.setName(updatedCategory.getName());
            }
            if (updatedCategory.getBudget() != null) {
                category.setBudget(updatedCategory.getBudget());
            }
            categoryRepository.save(category);
            return category;
        } catch (Exception e) {
            return null;
        }
    }


}
