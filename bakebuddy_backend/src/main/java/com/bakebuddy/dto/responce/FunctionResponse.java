package com.bakebuddy.dto.responce;

import com.bakebuddy.dto.OrderHistory;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse {
    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;
}
