package com.bakebuddy.dto;

import com.bakebuddy.model.Order;
import com.bakebuddy.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;

    private ProductDto product;

    private String size;

    private int quantity;

    private Integer mrpPrice;

    private Integer sellingPrice;

    private Long userId;

}
