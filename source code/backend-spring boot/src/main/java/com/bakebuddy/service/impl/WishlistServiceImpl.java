package com.bakebuddy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.exception.WishlistNotFoundException;
import com.bakebuddy.model.Product;
import com.bakebuddy.model.User;
import com.bakebuddy.model.Wishlist;
import com.bakebuddy.repository.WishlistRepository;
import com.bakebuddy.service.WishlistService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;


    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        Wishlist wishlist = wishlistRepository.findByUserId(user.getId());
        if (wishlist == null) {
            wishlist = this.createWishlist(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException {
        Wishlist wishlist = this.getWishlistByUserId(user);
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);

        return wishlistRepository.save(wishlist);
    }


}

