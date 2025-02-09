package com.bakebuddy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.dto.request.AddItemRequest;
import com.bakebuddy.dto.responce.ApiResponse;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.CartItem;
import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.CartItemException;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.service.CartItemService;
import com.bakebuddy.service.CartService;
import com.bakebuddy.service.ProductService;
import com.bakebuddy.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    // Get user cart
    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user);
		System.out.println("cart - "+cart.getUser().getEmail());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Add item to cart
    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(user, product, req.getQuantity());
        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    // Remove item from cart
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt) throws CartItemException,UserException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse("Item removed from cart", true);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    // Update item quantity in cart
    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,
                                                   @RequestBody CartItem cartItem,
                                                   @RequestHeader("Authorization") String jwt) throws CartItemException,UserException {
        User user = userService.findUserProfileByJwt(jwt);

        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }
}
