package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.dto.RevenueChart;

public interface RevenueService {
    List<RevenueChart> getDailyRevenueForChart(int days, Long BakeryOwnerId);
    List<RevenueChart> getMonthlyRevenueForChart(int months,Long BakeryOwnerId);
    List<RevenueChart> getYearlyRevenueForChart(int years,Long BakeryOwnerId);
    List<RevenueChart> getHourlyRevenueForChart(Long BakeryOwnerId);
    List<RevenueChart> getRevenueChartByType(String type,Long BakeryOwnerId);
}
