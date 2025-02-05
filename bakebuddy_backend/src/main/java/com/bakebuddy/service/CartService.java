package com.bakebuddy.service;


import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.CartItem;
import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.ProductException;

public interface CartService {
	
	public CartItem addCartItem(User user,
								Product product,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);

}
