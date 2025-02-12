package com.bakebuddy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bakebuddy.exception.CartItemException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.model.CartItem;
import com.bakebuddy.model.User;
import com.bakebuddy.response.ApiResponse;
import com.bakebuddy.service.CartItemService;
import com.bakebuddy.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	private CartItemService cartItemService;
	private UserService userService;
	
	public CartItemController(CartItemService cartItemService, UserService userService) {
		this.cartItemService=cartItemService;
		this.userService=userService;
	}
	

}
