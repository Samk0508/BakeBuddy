package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.domain.AccountStatus;
import com.bakebuddy.model.Seller;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus status);
}
