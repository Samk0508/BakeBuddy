package com.bakebuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.dto.request.CreateProductRequest;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.Product;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.exception.CategoryNotFoundException;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.ProductService;
import com.bakebuddy.service.UserService;


@RestController
@RequestMapping("/owner/product")
public class BakeryOwnerProductController {

   @Autowired private  ProductService productService;
   @Autowired private  BakeryOwnerService bakeryOwnerService;
   @Autowired private  UserService userService;


    @GetMapping()
    public ResponseEntity<List<Product>> getProductBySellerId(
            @RequestHeader("Authorization") String jwt) throws ProductException, BakeryOwnerException {

    	BakeryOwner bakeryOwner=bakeryOwnerService.getBakeryOwnerProfile(jwt);

        List<Product> products = productService.getProductByBakeryOwnerId(bakeryOwner.getId());
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,

            @RequestHeader("Authorization")String jwt)
            throws UserException,
            ProductException, CategoryNotFoundException, BakeryOwnerException {

    	BakeryOwner bakeryOwner=bakeryOwnerService.getBakeryOwnerProfile(jwt);

        Product product = productService.createProduct(request, bakeryOwner);
        return new ResponseEntity<>(product, HttpStatus.CREATED);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(productId, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
