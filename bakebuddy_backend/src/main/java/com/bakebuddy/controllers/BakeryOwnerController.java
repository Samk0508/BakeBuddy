package com.bakebuddy.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.config.JwtProvider;
import com.bakebuddy.dto.responce.ApiResponse;
import com.bakebuddy.dto.responce.AuthResponse;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BakeryOwnerReport;
import com.bakebuddy.entites.VerificationCode;
import com.bakebuddy.enums.AccountStatus;
import com.bakebuddy.enums.UserRole;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.repository.VerificationCodeRepository;
import com.bakebuddy.service.BakeryOwnerReportService;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.EmailService;
import com.bakebuddy.service.VerificationService;
import com.bakebuddy.service.impl.CustomeUserServiceImplementation;
import com.bakebuddy.utils.OtpUtils;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/owner")
public class BakeryOwnerController {

   @Autowired private  BakeryOwnerService bakeryOwnerService;
   @Autowired private  BakeryOwnerReportService bakeryOwnerReportService;
   @Autowired private  EmailService emailService;
   @Autowired private  VerificationCodeRepository verificationCodeRepository;
   @Autowired private  VerificationService verificationService;
   @Autowired private  JwtProvider jwtProvider;
   @Autowired private  CustomeUserServiceImplementation customeUserServiceImplementation;


    @PostMapping("/sent/login-top")
    public ResponseEntity<ApiResponse> sentLoginOtp(@RequestBody VerificationCode req) throws MessagingException, BakeryOwnerException {
    	BakeryOwner BakeryOwner = bakeryOwnerService.getBakeryOwnerByEmail(req.getEmail());

        String otp = OtpUtils.generateOTP();
        VerificationCode verificationCode = verificationService.createVerificationCode(otp, req.getEmail());

        String subject = "BakeBuddy Login Otp";
        String text = "your login otp is - ";
        emailService.sendVerificationOtpEmail(req.getEmail(), verificationCode.getOtp(), subject, text);

        ApiResponse res = new ApiResponse();
        res.setMessage("otp sent");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/verify/login-top")
    public ResponseEntity<AuthResponse> verifyLoginOtp(@RequestBody VerificationCode req) throws MessagingException, BakeryOwnerException {
//        Seller savedSeller = sellerService.createSeller(seller);


        String otp = req.getOtp();
        String email = req.getEmail();
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BakeryOwnerException("wrong otp...");
        }

        Authentication authentication = authenticate(req.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();


        authResponse.setRole(UserRole.valueOf(roleName));

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username) {
        UserDetails userDetails = customeUserServiceImplementation.loadUserByUsername("bakeryowner_" + username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<BakeryOwner> verifyBakeryOwnerEmail(@PathVariable String otp) throws BakeryOwnerException {


        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BakeryOwnerException("wrong otp...");
        }

        BakeryOwner BakeryOwner = bakeryOwnerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(BakeryOwner, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BakeryOwner> createBakeryOwner(@RequestBody BakeryOwner bakeryOwner) throws BakeryOwnerException, MessagingException {
    	BakeryOwner savedBakeryOwner = bakeryOwnerService.createBakeryOwner(bakeryOwner);

        String otp = OtpUtils.generateOTP();
        VerificationCode verificationCode = verificationService.createVerificationCode(otp, bakeryOwner.getUser().getEmail());

        String subject = "Bakebuddy Email Verification Code";
        String text = "Welcome to Bakebuddy, verify your account using this link ";
        String frontend_url = "http://localhost:3000/verify-bakeryOwner/";
        emailService.sendVerificationOtpEmail(bakeryOwner.getUser().getEmail(), verificationCode.getOtp(), subject, text + frontend_url);
        return new ResponseEntity<>(savedBakeryOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BakeryOwner> getBakeryOwnerById(@PathVariable Long id) throws BakeryOwnerException {
    	BakeryOwner bakeryOwner = bakeryOwnerService.getBakeryOwnerById(id);
        return new ResponseEntity<>(bakeryOwner, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<BakeryOwner> getBakeryOwnerByJwt(
            @RequestHeader("Authorization") String jwt) throws BakeryOwnerException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        BakeryOwner bakeryOwner = bakeryOwnerService.getBakeryOwnerByEmail(email);
        return new ResponseEntity<>(bakeryOwner, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<BakeryOwnerReport> getBakeryOwnerReport(
            @RequestHeader("Authorization") String jwt) throws BakeryOwnerException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        BakeryOwner bakeryOwner = bakeryOwnerService.getBakeryOwnerByEmail(email);
        BakeryOwnerReport report = bakeryOwnerReportService.getBakeryOwnerReport(bakeryOwner);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BakeryOwner>> getAllBakeryOwners(
            @RequestParam(required = false) AccountStatus status) {
        List<BakeryOwner> bakeryOwner = bakeryOwnerService.getAllBakeryOwner(status);
        return ResponseEntity.ok(bakeryOwner);
    }

    @PatchMapping()
    public ResponseEntity<BakeryOwner> updateBakeryOwner(
            @RequestHeader("Authorization") String jwt, @RequestBody BakeryOwner bakeryOwner) throws BakeryOwnerException {

    	BakeryOwner profile = bakeryOwnerService.getBakeryOwnerProfile(jwt);
    	BakeryOwner updatedBekaryOwner = bakeryOwnerService.updateBakeryOwner(profile.getId(),bakeryOwner);
        return ResponseEntity.ok(updatedBekaryOwner);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBakeryOwner(@PathVariable Long id) throws BakeryOwnerException {

        bakeryOwnerService.deleteBakeryOwner(id);
        return ResponseEntity.noContent().build();

    }
}
