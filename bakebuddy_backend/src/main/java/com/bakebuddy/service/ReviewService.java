package com.bakebuddy.service;

import java.util.List;

import javax.naming.AuthenticationException;

import com.bakebuddy.dto.request.CreateReviewRequest;
import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.Review;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.ReviewNotFoundException;


public interface ReviewService {

    Review createReview(CreateReviewRequest req,User user,Product product);

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId,String reviewText,
    		double rating,Long userId)throws ReviewNotFoundException, AuthenticationException;


    void deleteReview(Long reviewId, Long userId)throws ReviewNotFoundException, AuthenticationException;

}
