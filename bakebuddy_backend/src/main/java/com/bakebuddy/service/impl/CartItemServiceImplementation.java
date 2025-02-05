package com.bakebuddy.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.CartItem;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.CartItemException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.repository.CartItemRepository;
import com.bakebuddy.service.CartItemService;

@Service
public class CartItemServiceImplementation implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public CartItem updateCartItem(Long userId,Long id, CartItem cartItem)
			throws CartItemException, UserException {
		
		CartItem item=findCartItemById(id);
		User cartItemUser=item.getCart().getUser();
		
		if(cartItemUser.getId().equals(userId)) {
			
			item.setQuantity(cartItem.getQuantity());
			item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
			item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
			
			return cartItemRepository.save(item);
		}
		else {
			throw new CartItemException("You can't update  another users cart_item");
		}
		
	}
	

	@Override
	public void removeCartItem(Long userId,Long cartItemId)
			throws CartItemException,
			UserException {
		
		System.out.println("userId- "+userId+" cartItemId "+cartItemId);
		
		CartItem cartItem=findCartItemById(cartItemId);
		
		User cartItemUser=cartItem.getCart().getUser();

		if(cartItemUser.getId().equals(userId)) {
			cartItemRepository.deleteById(cartItem.getId());
		}
		else {
			throw new UserException("you can't remove anothor users item");
		}
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		return cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new CartItemException("cartItem not found with id : "+ cartItemId));
		
	}

}
