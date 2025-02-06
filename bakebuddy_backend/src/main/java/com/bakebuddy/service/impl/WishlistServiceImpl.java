package com.bakebuddy.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.User;
import com.bakebuddy.entites.Wishlist;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.exception.WishlistNotFoundException;
import com.bakebuddy.repository.WishlistRepository;
import com.bakebuddy.service.WishlistService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {
	@Autowired
    private WishlistRepository wishlistRepository;


	@Override
	public Wishlist createWishlist(User user) {
	    Wishlist wishlist = new Wishlist();
	    wishlist.setUser(user);
	    return wishlistRepository.save(wishlist);
	}

	@Override
	public Wishlist getWishlistByUserId(User user) throws WishlistNotFoundException {
	    Wishlist wishlist = wishlistRepository.findByUserId(user.getId());
	    if (wishlist == null) {
	        throw new WishlistNotFoundException("Wishlist not found for user with ID: " + user.getId());
	    }
	    return wishlist;
	}

	@Override
	public Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException, ProductException {
	    // Get user's wishlist, or throw an exception if not found
	    Wishlist wishlist = this.getWishlistByUserId(user);

	    // Check if product is null
	    if (product == null) {
	        throw new ProductException("Product not found");
	    }

	    // Add or remove product from the wishlist
	    if (wishlist.getProducts().contains(product)) {
	        wishlist.getProducts().remove(product);  // If product is already in wishlist, remove it
	    } else {
	        wishlist.getProducts().add(product);    // If not, add it
	    }

	    // Save the updated wishlist
	    return wishlistRepository.save(wishlist);
	}


}

