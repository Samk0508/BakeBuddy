package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
