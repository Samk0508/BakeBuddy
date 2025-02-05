package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.BakeryOwnerReport;

public interface BakeryOwnerReportRepository extends JpaRepository<BakeryOwnerReport,Long> {
	BakeryOwnerReport findByBakeryOwnerId(Long bakeryOwnerId);
}
