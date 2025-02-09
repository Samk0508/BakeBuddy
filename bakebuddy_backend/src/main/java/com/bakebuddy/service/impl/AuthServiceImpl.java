package com.bakebuddy.service.impl;


import java.util.Collection;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bakebuddy.config.JwtProvider;
import com.bakebuddy.dto.request.LoginRequest;
import com.bakebuddy.dto.request.SignupRequest;
import com.bakebuddy.dto.responce.AuthResponse;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.User;
import com.bakebuddy.entites.VerificationCode;
import com.bakebuddy.enums.UserRole;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.exception.UserException;
import com.bakebuddy.repository.CartRepository;
import com.bakebuddy.repository.UserRepository;
import com.bakebuddy.repository.VerificationCodeRepository;
import com.bakebuddy.service.AuthService;
import com.bakebuddy.service.EmailService;
import com.bakebuddy.service.UserService;
import com.bakebuddy.utils.OtpUtils;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    private final CustomeUserServiceImplementation customUserDetails;
    private final CartRepository cartRepository;


    @Override
    public void sentLoginOtp(String email) throws UserException, MessagingException {


        String SIGNING_PREFIX = "signing_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());
            userService.findUserByEmail(email);
        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);

        if (isExist != null) {
            verificationCodeRepository.delete(isExist);
        }

        String otp = OtpUtils.generateOTP();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "Bakebuddy Signup Otp";
        String text = "your login otp is - ";
        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }

   @Override
public String createUser(SignupRequest req) throws BakeryOwnerException {
    VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

    if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
        throw new BakeryOwnerException("Invalid OTP.");
    }

    // Check if user already exists
    User existingUser = userRepository.findByEmail(req.getEmail());
    if (existingUser != null) {
        throw new BakeryOwnerException("User already exists with this email.");
    }

    // Create new user
    User newUser = new User();
    newUser.setEmail(req.getEmail());
    newUser.setName(req.getName());
    newUser.setRole(UserRole.ROLE_CUSTOMER);
    newUser.setPhoneNumber(req.getPhoneNumber());
    newUser.setPassword(passwordEncoder.encode(req.getPassword())); // Store hashed password

    userRepository.save(newUser);

    // Create a cart for the user
    Cart cart = new Cart();
    cart.setUser(newUser);
    cartRepository.save(cart);

    // Clean up OTP after successful sign-up
    verificationCodeRepository.delete(verificationCode);

    return "User registered successfully. Please log in using your credentials.";
}

    @Override
    public AuthResponse signin(LoginRequest req) throws BakeryOwnerException {
        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {
            throw new BadCredentialsException("User not found.");
        }

        // Verify password
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        // Generate authentication token
        Authentication authentication = authenticate(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        // Build response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Login successful.");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        authResponse.setRole(UserRole.valueOf(roleName));

        return authResponse;
    }

	    private Authentication authenticate(String email, String password) throws BakeryOwnerException {
	        // Find user by email
	    	 UserDetails userDetails = customUserDetails.loadUserByUsername(email);
	        
	        if (userDetails == null) {
	            throw new BadCredentialsException("User not found for email: " + email);
	        }

	        // Validate password
	        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
	            throw new BadCredentialsException("Invalid password.");
	        }
	        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	    }

}
