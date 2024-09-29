package com.jtspringproject.JtSpringProject.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jtspringproject.JtSpringProject.dao.CategoryDao;
import com.jtspringproject.JtSpringProject.models.Category;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryDao categoryDao;
    
    public Category addCategory(String name) {
        try {
            Category category = categoryDao.addCategory(name);
            logger.info("Category added successfully: {}", category);
            return category;
        } catch (Exception e) {
            logger.error("Error adding category: {}", e.getMessage(), e);
            throw new RuntimeException("Error adding category", e);
        }
    }
    
    public List<Category> getCategories() {
        try {
            return categoryDao.getCategories();
        } catch (Exception e) {
            logger.error("Error retrieving categories: {}", e.getMessage(), e);
            throw new RuntimeException("Error retrieving categories", e);
        }
    }
    
    public Boolean deleteCategory(String id) {
        try {
            Boolean result = categoryDao.deleteCategory(id);
            if (result) {
                logger.info("Category deleted successfully with id: {}", id);
            } else {
                logger.warn("Category with id {} not found", id);
            }
            return result;
        } catch (Exception e) {
            logger.error("Error deleting category with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error deleting category", e);
        }
    }
    
    public Category updateCategory(String id, String name) {
        try {
            Category category = categoryDao.updateCategory(id, name);
            if (category != null) {
                logger.info("Category updated successfully: {}", category);
            } else {
                logger.warn("Category with id {} not found", id);
            }
            return category;
        } catch (Exception e) {
            logger.error("Error updating category with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error updating category", e);
        }
    }

    public Category getCategory(String id) {
        try {
            Category category = categoryDao.getCategory(id);
            if (category != null) {
                logger.info("Category retrieved successfully: {}", category);
            } else {
                logger.warn("Category with id {} not found", id);
            }
            return category;
        } catch (Exception e) {
            logger.error("Error retrieving category with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error retrieving category", e);
        }
    }
}
