package com.bakebuddy.service;


import com.bakebuddy.exception.OrderException;
import com.bakebuddy.model.OrderItem;
import com.bakebuddy.model.Product;

public interface OrderItemService {

	OrderItem getOrderItemById(Long id) throws Exception;
	


}
