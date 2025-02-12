package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
