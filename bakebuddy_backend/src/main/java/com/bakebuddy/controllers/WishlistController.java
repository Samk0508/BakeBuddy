package com.bakebuddy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.User;
import com.bakebuddy.entites.Wishlist;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.exception.WishlistNotFoundException;
import com.bakebuddy.service.ProductService;
import com.bakebuddy.service.UserService;
import com.bakebuddy.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {
	@Autowired
    private  WishlistService wishlistService;
	@Autowired
	private  ProductService productService;
	@Autowired
	private  UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody User user) {
        Wishlist wishlist = wishlistService.createWishlist(user);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(
            @RequestHeader("Authorization") String jwt) throws UserException,WishlistNotFoundException {

        User user = userService.findUserProfileByJwt(jwt);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/addproduct/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws WishlistNotFoundException, ProductException, UserException {

        Product product = productService.findProductById(productId);
        User user=userService.findUserProfileByJwt(jwt);
        Wishlist updatedWishlist = wishlistService.addProductToWishlist(
                user,
                product
        );
        return ResponseEntity.ok(updatedWishlist);

    }

}



