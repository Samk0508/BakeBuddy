package com.bakebuddy.service;


import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.User;
import com.bakebuddy.entites.Wishlist;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.exception.WishlistNotFoundException;

public interface WishlistService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user) throws WishlistNotFoundException;

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException, ProductException;

}

