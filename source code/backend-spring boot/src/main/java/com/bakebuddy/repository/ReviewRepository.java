package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.Product;
import com.bakebuddy.model.Review;
import com.bakebuddy.model.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findReviewsByUserId(Long userId);
    List<Review> findReviewsByProductId(Long productId);
}
