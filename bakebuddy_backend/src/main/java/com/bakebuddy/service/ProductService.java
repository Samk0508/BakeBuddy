package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.entites.Product;

public interface ProductService {
  //  public Product createProduct(CreateProductRequest req, Seller seller) throws ProductException;

    public void deleteProduct(Long productId);// throws ProductException;

    public Product updateProduct(Long productId,Product product);//throws ProductException;


    public Product findProductById(Long productId);//throws ProductException;


    public List<Product> searchProduct(String query);

//   public Page<Product> getAllProduct(String category,
//                                      String brand,
//                                      String colors,
//                                      String sizes,
//                                      Integer minPrice,
//                                      Integer maxPrice,
//                                      Integer minDiscount,
//                                      String sort,
//                                      String stock,
//                                      Integer pageNumber);

    public List<Product> recentlyAddedProduct();
    List<Product> getProductByBakeryOwnerId(Long bakeryOwnerId);
}
