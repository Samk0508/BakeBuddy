package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
