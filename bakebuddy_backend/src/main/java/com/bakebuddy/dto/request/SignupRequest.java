package com.bakebuddy.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String otp;
}
