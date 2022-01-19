package com.example.demo.categories;

import com.example.demo.categories.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findCategoryById(Integer id);
}
