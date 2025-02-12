package com.bakebuddy.service;

import javax.naming.AuthenticationException;

import com.bakebuddy.exception.ReviewNotFoundException;
import com.bakebuddy.model.Product;
import com.bakebuddy.model.Review;
import com.bakebuddy.model.User;
import com.bakebuddy.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product);

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId,
                        String reviewText,
                        double rating,
                        Long userId) throws ReviewNotFoundException, AuthenticationException;


    void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException;

}
