package com.jtspringproject.JtSpringProject.repositories;

import com.jtspringproject.JtSpringProject.models.CartProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartProductRepository extends MongoRepository<CartProduct, String> {
    // Custom query methods can be added here if needed
}
