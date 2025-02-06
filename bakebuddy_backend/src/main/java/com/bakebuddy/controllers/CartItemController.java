package com.bakebuddy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.service.CartItemService;
import com.bakebuddy.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private UserService userService;
	
	public CartItemController(CartItemService cartItemService, UserService userService) {
		this.cartItemService=cartItemService;
		this.userService=userService;
	}
	

}
