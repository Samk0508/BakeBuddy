package com.bakebuddy.dto;

import lombok.Data;

import java.util.List;

import com.bakebuddy.model.User;

@Data
public class OrderHistory {
    private Long id;
    private UserDto user;
    private List<OrderDto> currentOrders;
    private int totalOrders;
    private int cancelledOrders;
    private int completedOrders;
    private int pendingOrders;
}
