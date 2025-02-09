package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.Coupon;
import com.bakebuddy.entites.User;
import com.bakebuddy.exception.CouponNotValidException;
import com.bakebuddy.exception.UserException;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws CouponNotValidException;
    Cart removeCoupon(String code, User user) throws CouponNotValidException;
    Coupon createCoupon(Coupon coupon);
    void deleteCoupon(Long couponId);
    List<Coupon> getAllCoupons();
    
    Coupon getCouponById(Long couponId);
}
