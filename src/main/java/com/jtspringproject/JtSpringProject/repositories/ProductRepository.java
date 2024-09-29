package com.jtspringproject.JtSpringProject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.jtspringproject.JtSpringProject.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Custom query methods can be defined here if needed
}
