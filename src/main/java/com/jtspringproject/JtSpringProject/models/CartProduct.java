package com.jtspringproject.JtSpringProject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_products")
public class CartProduct {

    @Id
    private String id;  // Unique identifier for CartProduct in MongoDB

    @DBRef
    private Cart cart;  // Reference to a Cart document

    @DBRef
    private Product product;  // Reference to a Product document

    public CartProduct() {
        // Default constructor
    }

    public CartProduct(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Convenience methods to get Cart and Product IDs
    public String getCartId() {
        return cart != null ? cart.getId() : null;
    }

    public String getProductId() {
        return product != null ? product.getId() : null;
    }
}
