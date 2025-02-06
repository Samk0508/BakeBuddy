package com.bakebuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.entites.Product;
import com.bakebuddy.exception.ProductException;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.ProductService;
import com.bakebuddy.service.UserService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
    private  ProductService productService;
	@Autowired
    private  UserService userService;
	@Autowired
    private  BakeryOwnerService bakeryOwnerService;



    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductException {

            Product product = productService.findProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(required = false) String query) {
        List<Product> products = productService.searchProduct(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(required = false) String category,
                                                        @RequestParam(required = false) Integer minPrice,
                                                        @RequestParam(required = false) Integer maxPrice,
                                                        @RequestParam(required = false) Integer minDiscount,
                                                        @RequestParam(required = false) String sort,
                                                        @RequestParam(required = false) String stock,
                                                        @RequestParam(defaultValue = "0") Integer pageNumber) {
        System.out.println("color p -------- "+pageNumber);
        return new ResponseEntity<>(
                productService.getAllProduct(category,minPrice,
                        maxPrice, minDiscount, sort,
                        stock, pageNumber), HttpStatus.OK);
    }
}
