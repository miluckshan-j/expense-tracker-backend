package com.example.demo.categories;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findCategoryById(Integer id);
}
