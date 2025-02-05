package com.bakebuddy.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;

    private ProductDto product;

    private int quantity;

    private Integer mrpPrice;

    private Integer sellingPrice;

    private Long userId;
}
