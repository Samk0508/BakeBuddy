package com.bakebuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.entites.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findReviewsByUser_Id(Long userId);
    List<Review> findReviewsByProduct_Id(Long productId);
}
