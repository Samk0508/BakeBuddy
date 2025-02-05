package com.bakebuddy.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bakebuddy.entites.Address;
import com.bakebuddy.entites.PaymentDetails;
import com.bakebuddy.enums.OrderStatus;
import com.bakebuddy.enums.PaymentStatus;

import lombok.Data;

@Data
public class OrderDto {

    private Long id;

    private String orderId;

    private UserDto user;

    private Long bakeryOwnerId;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Address shippingAddress;

    private PaymentDetails paymentDetails=new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;

    private OrderStatus orderStatus;

    private int totalItem;

    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusMinutes(30);
}
