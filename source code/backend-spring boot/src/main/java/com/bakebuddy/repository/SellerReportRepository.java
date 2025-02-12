package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport,Long> {
    SellerReport findBySellerId(Long sellerId);
}
