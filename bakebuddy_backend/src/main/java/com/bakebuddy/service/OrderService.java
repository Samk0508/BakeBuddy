package com.bakebuddy.service;

import java.util.List;
import java.util.Set;

import com.bakebuddy.entites.Address;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.User;
import com.bakebuddy.enums.OrderStatus;
import com.bakebuddy.exception.OrderException;


public interface OrderService {
	
	public Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	
	public Order findOrderById(Long orderId)throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public List<Order>getShopsOrders(Long bakeryOwnerId);

	public Order updateOrderStatus(Long orderId,OrderStatus orderStatus)throws OrderException;
	
	public void deleteOrder(Long orderId) throws OrderException;

	Order cancelOrder(Long orderId,User user) throws OrderException;
	
}
