package com.bakebuddy.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bakebuddy.domain.OrderStatus;
import com.bakebuddy.domain.PaymentStatus;
import com.bakebuddy.model.Address;
import com.bakebuddy.model.OrderItem;
import com.bakebuddy.model.PaymentDetails;
import com.bakebuddy.model.User;

@Data
public class OrderDto {

    private Long id;

    private String orderId;

    private UserDto user;

    private Long sellerId;

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
    private LocalDateTime deliverDate = orderDate.plusDays(7);

}
