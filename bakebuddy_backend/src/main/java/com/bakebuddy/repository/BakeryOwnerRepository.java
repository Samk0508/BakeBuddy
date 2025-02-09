package com.bakebuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.enums.AccountStatus;


public interface BakeryOwnerRepository extends JpaRepository<BakeryOwner,Long> {

	BakeryOwner findByUserEmail(String email); 
    List<BakeryOwner> findByAccountStatus(AccountStatus status);
}
