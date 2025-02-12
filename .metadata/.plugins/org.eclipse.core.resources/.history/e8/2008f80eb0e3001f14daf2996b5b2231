package com.bakebuddy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bakebuddy.entites.Product;

public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

   
    List<Product> findByBakerydetails_Id(Long id);

    
    @Query("SELECT p FROM Product p WHERE (:query IS NULL OR LOWER(p.title) " +
            "LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "OR (:query IS NULL OR LOWER(p.category.name) " +
            "LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "OR (:query IS NULL OR p.category.id = :query)")
List<Product> searchProduct(@Param("query") String query);

    
    


}
