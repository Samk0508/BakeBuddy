package com.bakebuddy.service.impl;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.dto.request.CreateReviewRequest;
import com.bakebuddy.entites.Product;
import com.bakebuddy.entites.Review;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.ReviewNotFoundException;
import com.bakebuddy.repository.ReviewRepository;
import com.bakebuddy.service.ReviewService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	@Autowired
    private ReviewRepository reviewRepository;


    @Override
    public Review createReview(CreateReviewRequest req,
                               User user,
                               Product product) {
        Review newReview = new Review();

        newReview.setReviewText(req.getReviewText());
        newReview.setRating(req.getReviewRating());
        newReview.setUser(user);
        newReview.setProduct(product);

        product.getReviews().add(newReview);

        return reviewRepository.save(newReview);
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findReviewsByProduct_Id(productId);
    }


    @Override
    public Review updateReview(Long reviewId,
                               String reviewText,
                               double rating,
                               Long userId) throws ReviewNotFoundException, AuthenticationException {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review Not found"));

        if(review.getUser().getId()!=userId){
            throw new AuthenticationException("You do not have permission to delete this review");
        }

        review.setReviewText(reviewText);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId,Long userId) throws ReviewNotFoundException,
            AuthenticationException {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review Not found"));
        if(review.getUser().getId()!=userId){
            throw new AuthenticationException("You do not have permission to delete this review");
        }
        reviewRepository.delete(review);
    }

}
