package com.bakebuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.dto.RevenueChart;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.RevenueService;

@RestController
@RequestMapping("/api/owner/revenue/chart")
public class RevenueController {
	@Autowired
	private RevenueService revenueService;
	@Autowired
	private BakeryOwnerService bakeryOwnerService;

    @GetMapping()
    public ResponseEntity<List<RevenueChart>> getRevenueChart(
            @RequestParam(defaultValue = "today") String type,
            @RequestHeader("Authorization") String jwt) throws BakeryOwnerException {
    	BakeryOwner bakeryOwner = bakeryOwnerService.getBakeryOwnerProfile(jwt);
        List<RevenueChart> revenue = revenueService
                .getRevenueChartByType(type, bakeryOwner.getId());
        return ResponseEntity.ok(revenue);
    }

}
