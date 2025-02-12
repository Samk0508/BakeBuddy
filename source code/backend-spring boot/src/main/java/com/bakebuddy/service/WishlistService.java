package com.bakebuddy.service;


import java.util.Optional;

import com.bakebuddy.exception.WishlistNotFoundException;
import com.bakebuddy.model.Product;
import com.bakebuddy.model.User;
import com.bakebuddy.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user);

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException;

}

