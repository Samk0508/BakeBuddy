package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
}
