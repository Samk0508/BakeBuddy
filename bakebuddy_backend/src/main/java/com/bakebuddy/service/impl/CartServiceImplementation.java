package com.bakebuddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.CartItem;
import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.repository.CartItemRepository;
import com.bakebuddy.repository.CartRepository;
import com.bakebuddy.service.CartService;
import com.bakebuddy.service.ProductService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CartServiceImplementation implements CartService {
	@Autowired
	private  CartRepository cartRepository;
	@Autowired
	private  CartItemRepository cartItemRepository;
	@Autowired
	private  ProductService productService;
	

	public Cart findUserCart(User user) {
		Cart cart =	cartRepository.findByUser_Id(user.getId());

		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartsItem : cart.getCartItems()) {
			totalPrice+=cartsItem.getMrpPrice();
			totalDiscountedPrice+=cartsItem.getSellingPrice();
			totalItem+=cartsItem.getQuantity();
		}

		cart.setTotalMrpPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalSellingPrice(totalDiscountedPrice-cart.getCouponPrice());
		cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
		cart.setTotalItem(totalItem);
		
		return cartRepository.save(cart);
		
	}

	public static int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
		if (mrpPrice <= 0) {
			return 0;
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentage = (discount / mrpPrice) * 100;
		return (int) discountPercentage;
	}

	@Override
	public CartItem addCartItem(User user,Product product,int quantity) throws ProductException {
		Cart cart=findUserCart(user);
		
		CartItem isPresent=cartItemRepository.findByCartAndProduct(cart, product);
		
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);

			cartItem.setQuantity(quantity);
			cartItem.setUserId(user.getId());
			
			int totalPrice=quantity*product.getSellingPrice();
			cartItem.setSellingPrice(totalPrice);
			cartItem.setMrpPrice(quantity*product.getMrpPrice());

			cart.getCartItems().add(cartItem);
			cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
		}

		return isPresent;
	}

}
