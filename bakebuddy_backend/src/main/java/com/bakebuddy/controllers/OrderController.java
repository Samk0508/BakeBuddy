package com.bakebuddy.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.dto.responce.PaymentLinkResponse;
import com.bakebuddy.entites.Address;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BakeryOwnerReport;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.OrderItem;
import com.bakebuddy.entites.PaymentOrder;
import com.bakebuddy.entites.User;
import com.bakebuddy.enums.PaymentMethod;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.exception.OrderException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.repository.PaymentOrderRepository;
import com.bakebuddy.service.BakeryOwnerReportService;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.CartService;
import com.bakebuddy.service.OrderItemService;
import com.bakebuddy.service.OrderService;
import com.bakebuddy.service.PaymentService;
import com.bakebuddy.service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired private  OrderService orderService;
	@Autowired private  UserService userService;
	@Autowired private  OrderItemService orderItemService;
	@Autowired private  CartService cartService;
	@Autowired private  PaymentService paymentService;
	@Autowired private  PaymentOrderRepository paymentOrderRepository;
	@Autowired private  BakeryOwnerReportService bakeryOwnerReportService;
	@Autowired private  BakeryOwnerService bakeryOwnerService;

	
	@PostMapping()
	public ResponseEntity<PaymentLinkResponse> createOrderHandler(
			@RequestBody Address spippingAddress,
			@RequestParam PaymentMethod paymentMethod,
			@RequestHeader("Authorization")String jwt)
            throws UserException, RazorpayException {
		
		User user=userService.findUserProfileByJwt(jwt);
		Cart cart=cartService.findUserCart(user);
		Set<Order> orders =orderService.createOrder(user, spippingAddress,cart);

		PaymentOrder paymentOrder=paymentService.createOrder(user,orders);

		PaymentLinkResponse res = new PaymentLinkResponse();

//		if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
			PaymentLink payment=paymentService.createRazorpayPaymentLink(user,
					paymentOrder.getAmount(),
					paymentOrder.getId());
			String paymentUrl=payment.get("short_url");
			String paymentUrlId=payment.get("id");


			res.setPayment_link_url(paymentUrl);
//			res.setPayment_link_id(paymentUrlId);
			paymentOrder.setPaymentLinkId(paymentUrlId);
			paymentOrderRepository.save(paymentOrder);
//		}
//		else{
//			String paymentUrl=paymentService.createStripePaymentLink(user,
//					paymentOrder.getAmount(),
//					paymentOrder.getId());
//			res.setPayment_link_url(paymentUrl);
//		}
		return new ResponseEntity<>(res,HttpStatus.OK);

	}
	
	@GetMapping("/user")
	public ResponseEntity< List<Order>> usersOrderHistoryHandler(
			@RequestHeader("Authorization")
	String jwt) throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		List<Order> orders=orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity< Order> getOrderById(@PathVariable Long orderId, @RequestHeader("Authorization")
	String jwt) throws OrderException, UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		Order orders=orderService.findOrderById(orderId);
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}

	@GetMapping("/item/{orderItemId}")
	public ResponseEntity<OrderItem> getOrderItemById(
			@PathVariable Long orderItemId, @RequestHeader("Authorization")
	String jwt) throws Exception {
		System.out.println("------- controller ");
		User user = userService.findUserProfileByJwt(jwt);
		OrderItem orderItem=orderItemService.getOrderItemById(orderItemId);
		return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrder(
			@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt
	) throws UserException, OrderException, BakeryOwnerException {
		User user=userService.findUserProfileByJwt(jwt);
		Order order=orderService.cancelOrder(orderId,user);

		BakeryOwner bakeryOwner= bakeryOwnerService.getBakeryOwnerById(order.getBakeryDetails().getBakeryOwner().getId());
		BakeryOwnerReport report=bakeryOwnerReportService.getBakeryOwnerReport(bakeryOwner);

		report.setCanceledOrders(report.getCanceledOrders()+1);
		report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
		bakeryOwnerReportService.updateBakeryOwnerReport(report);

		return ResponseEntity.ok(order);
	}

}
