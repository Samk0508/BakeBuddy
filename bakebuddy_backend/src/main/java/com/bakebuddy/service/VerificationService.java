package com.bakebuddy.service;

import com.bakebuddy.entites.VerificationCode;

public interface VerificationService {

    VerificationCode createVerificationCode(String otp, String email);
}
