package com.bakebuddy.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findByUser_Id(Long userId);

    List<Order> findByBakeryOwner_IdOrderByOrderDateDesc(Long bakeryOwnerId);
    List<Order> findByBakeryOwner_IdAndOrderDateBetween(Long bakeryOwnerId,LocalDateTime startDate, LocalDateTime endDate);

}
