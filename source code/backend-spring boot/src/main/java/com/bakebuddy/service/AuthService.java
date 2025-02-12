package com.bakebuddy.service;

import com.bakebuddy.exception.SellerException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.request.LoginRequest;
import com.bakebuddy.request.ResetPasswordRequest;
import com.bakebuddy.request.SignupRequest;
import com.bakebuddy.response.ApiResponse;
import com.bakebuddy.response.AuthResponse;

import jakarta.mail.MessagingException;

public interface AuthService {

    void sentLoginOtp(String email) throws UserException, MessagingException;
    String createUser(SignupRequest req) throws SellerException;
    AuthResponse signin(LoginRequest req) throws SellerException;

}
