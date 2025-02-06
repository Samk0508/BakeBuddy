package com.bakebuddy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bakebuddy.dto.request.CreateProductRequest;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.Product;
import com.bakebuddy.exception.ProductException;

public interface ProductService {
    public Product createProduct(CreateProductRequest req, BakeryOwner bakeryOwner) throws ProductException;

    public void deleteProduct(Long productId)throws ProductException;

    public Product updateProduct(Long productId,Product product)throws ProductException;

    public Product findProductById(Long productId)throws ProductException;

    public List<Product> searchProduct(String query);

   public Page<Product> getAllProduct(String category,
                                      Integer minPrice,
                                      Integer maxPrice,
                                      Integer minDiscount,
                                      String sort,
                                      String stock,
                                      Integer pageNumber);

    public List<Product> recentlyAddedProduct();
    
    public  List<Product> getProductByBakeryOwnerId(Long bakeryOwnerId);
}
