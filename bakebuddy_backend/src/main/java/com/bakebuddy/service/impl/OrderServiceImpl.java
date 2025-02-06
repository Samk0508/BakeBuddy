package com.bakebuddy.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.Address;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.CartItem;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.OrderItem;
import com.bakebuddy.entites.User;
import com.bakebuddy.enums.OrderStatus;
import com.bakebuddy.enums.PaymentStatus;
import com.bakebuddy.exception.OrderException;
import com.bakebuddy.repository.AddressRepository;
import com.bakebuddy.repository.OrderItemRepository;
import com.bakebuddy.repository.OrderRepository;
import com.bakebuddy.repository.UserRepository;
import com.bakebuddy.service.CartService;
import com.bakebuddy.service.OrderItemService;
import com.bakebuddy.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private  OrderRepository orderRepository;
	@Autowired
	private  CartService cartService;
	@Autowired
	private  AddressRepository addressRepository;
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  OrderItemService orderItemService;
	@Autowired
	private  OrderItemRepository orderItemRepository;
	


	@Override
	public Set<Order> createOrder(User user, Address shippAddress, Cart cart) {
		
//		shippAddress.setUser(user);
		if(!user.getAddresses().contains(shippAddress)){
			user.getAddresses().add(shippAddress);
		}


		Address address= addressRepository.save(shippAddress);



		Map<Long, List<CartItem>> itemsByBakery = cart.getCartItems().stream()
				.collect(Collectors.groupingBy(item -> item.getProduct().getBakerydetails().getId()));

		Set<Order> orders=new HashSet<>();

		for(Map.Entry<Long, List<CartItem>> entry:itemsByBakery.entrySet()){
			Long BakerydetailsId=entry.getKey();
			List<CartItem> cartItems=entry.getValue();

			int totalOrderPrice = cartItems.stream().mapToInt(CartItem::getSellingPrice).sum();
			int totalItem=cartItems.stream().mapToInt(CartItem::getQuantity).sum();

			Order createdOrder=new Order();
			createdOrder.setUser(user);
			createdOrder.setBakeryDetails(cartItems.get(0).getProduct().getBakerydetails());
			createdOrder.setTotalMrpPrice(totalOrderPrice);
			createdOrder.setTotalSellingPrice(totalOrderPrice);
			createdOrder.setTotalItem(totalItem);
			createdOrder.setShippingAddress(address);
			createdOrder.setOrderStatus(OrderStatus.PENDING);
			createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

			Order savedOrder=orderRepository.save(createdOrder);
			orders.add(savedOrder);


			List<OrderItem> orderItems=new ArrayList<>();

			for(CartItem item: cartItems) {
				OrderItem orderItem=new OrderItem();

				orderItem.setOrder(savedOrder);
				orderItem.setMrpPrice(item.getMrpPrice());
				orderItem.setProduct(item.getProduct());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setUserId(item.getUserId());
				orderItem.setSellingPrice(item.getSellingPrice());

				savedOrder.getOrderItems().add(orderItem);

				OrderItem createdOrderItem=orderItemRepository.save(orderItem);

				orderItems.add(createdOrderItem);
			}

		}
		
		return orders;
		
	}

	  @Override
	    public Order findOrderById(Long orderId) throws OrderException {
	        return orderRepository.findById(orderId)
	                .orElseThrow(() -> new OrderException("Order not found with id " + orderId));
	    }

	@Override
	public List<Order> usersOrderHistory(Long userId) {

		return orderRepository.findByUser_Id(userId);
	}

	@Override
	public List<Order> getShopsOrders(Long bakeryOwnerId) {

		return orderRepository.findByBakeryDetails_BakeryOwner_IdOrderByOrderDateDesc(bakeryOwnerId);
	}

	@Override
	public Order updateOrderStatus(Long orderId, OrderStatus orderStatus)
			throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(orderStatus);
		return orderRepository.save(order);
	}


	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		if (!orderRepository.existsById(orderId)) {
	        throw new OrderException("Order not found with ID: " + orderId);
	    }

	    orderRepository.deleteById(orderId);
	}

	@Override
	public Order cancelOrder(Long orderId, User user) throws OrderException {
		Order order = findOrderById(orderId);

	    // Ensure the user requesting cancellation is the order owner
	    if (!user.getId().equals(order.getUser().getId())) {
	        throw new OrderException("Unauthorized: You cannot cancel order " + orderId);
	    }

	    // Ensure the order is not already canceled
	    if (order.getOrderStatus() == OrderStatus.CANCELLED) {
	        throw new OrderException("Order " + orderId + " is already canceled.");
	    }

	    // Ensure only orders in a valid status can be canceled
	    if (order.getOrderStatus() == OrderStatus.PLACED || order.getOrderStatus() == OrderStatus.SHIPPED) {
	        throw new OrderException("Order " + orderId + " cannot be canceled as it is already " + order.getOrderStatus());
	    }

	    // Update order status
	    order.setOrderStatus(OrderStatus.CANCELLED);
    
//	    // If there's a payment associated, update its status too (Optional)
//	    if (order.getPaymentDetails() != null) {
//	        order.getPaymentDetails().setStatus(PaymentStatus.REFUND_INITIATED);
//	    }

	    return orderRepository.save(order);
	}

}
