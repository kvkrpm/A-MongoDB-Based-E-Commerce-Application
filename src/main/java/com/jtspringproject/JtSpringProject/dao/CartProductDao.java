package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.CartProduct;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.repositories.CartProductRepository;
import com.jtspringproject.JtSpringProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class CartProductDao {

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add a product to the cart
    public CartProduct addCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    // Get all cart products
    public List<CartProduct> getCartProducts() {
        return cartProductRepository.findAll();
    }

    // Get products by cart ID
    public List<Product> getProductsByCartId(String cartId) {
        if (cartId == null || cartId.isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be null or empty");
        }

        List<CartProduct> cartProducts = cartProductRepository.findAll().stream()
            .filter(cartProduct -> cartProduct.getCartId().equals(cartId))
            .collect(Collectors.toList());

        List<String> productIds = cartProducts.stream()
            .map(CartProduct::getProductId)
            .collect(Collectors.toList());

        Iterable<Product> productsIterable = productRepository.findAllById(productIds);
        
        // Convert Iterable<Product> to List<Product>
        List<Product> products = StreamSupport.stream(productsIterable.spliterator(), false)
            .collect(Collectors.toList());

        return products;
    }

    // Update a cart product
    public void updateCartProduct(CartProduct cartProduct) {
        if (cartProduct == null || cartProduct.getId() == null) {
            throw new IllegalArgumentException("CartProduct or CartProduct ID cannot be null");
        }
        cartProductRepository.save(cartProduct);
    }

    // Delete a cart product
    public void deleteCartProduct(CartProduct cartProduct) {
        if (cartProduct == null || cartProduct.getId() == null) {
            throw new IllegalArgumentException("CartProduct or CartProduct ID cannot be null");
        }
        cartProductRepository.delete(cartProduct);
    }
}
