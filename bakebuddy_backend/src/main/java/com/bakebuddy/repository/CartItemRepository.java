package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.CartItem;
import com.bakebuddy.entites.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProduct(Cart cart, Product product);


}
