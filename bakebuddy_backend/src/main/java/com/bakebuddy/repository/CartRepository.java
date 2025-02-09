package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	 Cart findByUser_Id(Long userId);
}
