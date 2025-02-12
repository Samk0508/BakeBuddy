package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.Cart;
import com.bakebuddy.model.CartItem;
import com.bakebuddy.model.Product;
import com.bakebuddy.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);


}
