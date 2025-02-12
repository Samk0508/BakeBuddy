package com.bakebuddy.service;

import com.bakebuddy.exception.CartItemException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.model.Cart;
import com.bakebuddy.model.CartItem;
import com.bakebuddy.model.Product;


public interface CartItemService {
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
