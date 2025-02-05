package com.bakebuddy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByBakeryOwnerId(Long bakeryOwnerId);
}
