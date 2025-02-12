package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    VerificationCode findByEmail(String email);
    VerificationCode findByOtp(String otp);
}
