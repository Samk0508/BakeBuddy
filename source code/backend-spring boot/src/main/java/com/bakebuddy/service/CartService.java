package com.bakebuddy.service;

import com.bakebuddy.exception.ProductException;
import com.bakebuddy.model.Cart;
import com.bakebuddy.model.CartItem;
import com.bakebuddy.model.Product;
import com.bakebuddy.model.User;
import com.bakebuddy.request.AddItemRequest;

public interface CartService {
	
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);

}
