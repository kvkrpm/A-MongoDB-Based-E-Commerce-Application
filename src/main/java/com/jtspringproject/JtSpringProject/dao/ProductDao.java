package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;

    // Fetch all products
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Get a product by ID
    public Product getProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    // Update a product
    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        }
        return null; // Or throw an exception if preferred
    }

    // Delete a product
    public Boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
