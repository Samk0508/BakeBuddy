package com.bakebuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Payouts;
import com.bakebuddy.enums.PayoutsStatus;

public interface PayoutsRepository extends JpaRepository<Payouts,Long> {

    List<Payouts> findPayoutsByBakeryOwnerId(Long bakeryOwnerId);
    List<Payouts> findAllByStatus(PayoutsStatus status);
}
