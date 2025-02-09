package com.bakebuddy.service;

import com.bakebuddy.dto.request.LoginRequest;
import com.bakebuddy.dto.request.SignupRequest;
import com.bakebuddy.dto.responce.AuthResponse;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.exception.UserException;

import jakarta.mail.MessagingException;



public interface AuthService {

    void sentLoginOtp(String email) throws UserException,MessagingException;
    String createUser(SignupRequest req) throws UserException, BakeryOwnerException;
    AuthResponse signin(LoginRequest req) throws BakeryOwnerException;

}
