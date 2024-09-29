package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CartDao {

    @Autowired
    private CartRepository cartRepository;

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public void updateCart(Cart cart) {
        cartRepository.save(cart);  // save() will update if the cart already exists
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
