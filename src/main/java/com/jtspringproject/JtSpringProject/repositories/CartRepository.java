package com.jtspringproject.JtSpringProject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.jtspringproject.JtSpringProject.models.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {
    // Custom query methods can be defined here if needed
}
