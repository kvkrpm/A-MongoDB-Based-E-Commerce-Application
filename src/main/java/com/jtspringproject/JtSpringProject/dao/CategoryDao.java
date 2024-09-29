package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDao {

    @Autowired
    private CategoryRepository categoryRepository;

    // Add or update a category
    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    // Get all categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Delete a category by ID
    public Boolean deleteCategory(String id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Update a category
    public Category updateCategory(String id, String name) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(name);
            return categoryRepository.save(category);
        }
        return null; // Or throw an exception if preferred
    }

    // Get a category by ID
    public Category getCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }
}
