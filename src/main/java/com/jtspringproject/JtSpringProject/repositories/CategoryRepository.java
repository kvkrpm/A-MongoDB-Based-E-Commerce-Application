package com.jtspringproject.JtSpringProject.repositories;

import com.jtspringproject.JtSpringProject.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    // Additional query methods can be defined here if needed
}
