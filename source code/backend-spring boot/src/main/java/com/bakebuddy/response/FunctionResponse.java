package com.bakebuddy.response;

import com.bakebuddy.dto.OrderHistory;
import com.bakebuddy.model.Cart;
import com.bakebuddy.model.Order;
import com.bakebuddy.model.Product;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse {
    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;
}
