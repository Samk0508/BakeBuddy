package com.bakebuddy.service;

import com.bakebuddy.entites.CartItem;
import com.bakebuddy.exception.CartItemException;
import com.bakebuddy.exception.UserException;



public interface CartItemService {
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem)throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId)throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	
}
