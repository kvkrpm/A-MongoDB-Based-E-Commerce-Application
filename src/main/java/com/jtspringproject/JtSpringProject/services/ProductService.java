package com.jtspringproject.JtSpringProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // Return null if the product is not found
    }

    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false; // Return false if the product does not exist
        }
    }
}
