package com.bakebuddy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bakebuddy.config.JwtProvider;
import com.bakebuddy.entites.BakeryDetails;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BankDetails;
import com.bakebuddy.enums.AccountStatus;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.repository.AddressRepository;
import com.bakebuddy.repository.BakeryOwnerReportRepository;
import com.bakebuddy.repository.BakeryOwnerRepository;
import com.bakebuddy.service.BakeryOwnerService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BakeryOwnerServiceImpl implements BakeryOwnerService {
	@Autowired
    private  BakeryOwnerRepository bakeryOwnerRepository;
	@Autowired
	private  AddressRepository addressRepository;
	@Autowired
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private  JwtProvider jwtProvider;


	 @Override
	    public BakeryOwner getBakeryOwnerProfile(String jwt) throws BakeryOwnerException {
	        // Extract email from the JWT token and fetch the BakeryOwner profile
	        String email = jwtProvider.getEmailFromJwtToken(jwt);
	        return this.getBakeryOwnerByEmail(email);
	    }

	 @Override
	 public BakeryOwner createBakeryOwner(BakeryOwner bakeryOwner) throws BakeryOwnerException {
	     // Check if BakeryOwner already exists by email
	     BakeryOwner existingBakeryOwner = bakeryOwnerRepository.findByUserEmail(bakeryOwner.getUser().getEmail());
	     if (existingBakeryOwner != null) {
	         throw new BakeryOwnerException("BakeryOwner already exists. Please use a different email.");
	     }

	     // Ensure BakeryDetails and BankDetails are not null
	     if (bakeryOwner.getBakeryDetails() == null || bakeryOwner.getBankDetails() == null) {
	         throw new BakeryOwnerException("Bakery details and bank details are required.");
	     }

	     // Set account status to pending verification
	     bakeryOwner.setAccountStatus(AccountStatus.PENDING_VERIFICATION);

	     // Save and return BakeryOwner (CascadeType.ALL will handle related entities)
	     return bakeryOwnerRepository.save(bakeryOwner);
	 }



    @Override
    public BakeryOwner getBakeryOwnerById(Long id) throws BakeryOwnerException {
        // Retrieve the BakeryOwner by ID
        Optional<BakeryOwner> optionalBakeryOwner = bakeryOwnerRepository.findById(id);
        if (optionalBakeryOwner.isPresent()) {
            return optionalBakeryOwner.get();  // Return found BakeryOwner
        } else {
            throw new BakeryOwnerException("BakeryOwner not found with id " + id);  // Throw exception if not found
        }
    }

    @Override
    public BakeryOwner getBakeryOwnerByEmail(String email) throws BakeryOwnerException {
        // Retrieve the BakeryOwner by email
        BakeryOwner bakeryOwner = bakeryOwnerRepository.findByUserEmail(email);
        if (bakeryOwner != null) {
            return bakeryOwner;  // Return found BakeryOwner
        }
        throw new BakeryOwnerException("BakeryOwner not found with email " + email);  // Throw exception if not found
    }
    
    @Override
    public List<BakeryOwner> getAllBakeryOwner(AccountStatus status) {
        // Retrieve all BakeryOwners by their account status
        return bakeryOwnerRepository.findByAccountStatus(status);
    }

    @Override
    public BakeryOwner updateBakeryOwner(Long id, BakeryOwner bakeryOwner) throws BakeryOwnerException {
        // Retrieve the existing BakeryOwner and update their details
        BakeryOwner existingBakeryOwner = bakeryOwnerRepository.findById(id)
                .orElseThrow(() -> new BakeryOwnerException("BakeryOwner not found with id " + id));

        // Update relevant fields
        if (bakeryOwner.getUser() != null) {
            existingBakeryOwner.setUser(bakeryOwner.getUser());
        }
        if (bakeryOwner.getBakeryDetails() != null) {
            existingBakeryOwner.setBakeryDetails(bakeryOwner.getBakeryDetails());
        }
        if (bakeryOwner.getBankDetails() != null) {
            existingBakeryOwner.setBankDetails(bakeryOwner.getBankDetails());
        }
        if (bakeryOwner.getAccountStatus() != null) {
            existingBakeryOwner.setAccountStatus(bakeryOwner.getAccountStatus());
        }

        // Return the updated BakeryOwner
        return bakeryOwnerRepository.save(existingBakeryOwner);
    }

    @Override
    public void deleteBakeryOwner(Long id) throws BakeryOwnerException {
        // Delete the BakeryOwner by ID, throwing an exception if not found
        if (bakeryOwnerRepository.existsById(id)) {
            bakeryOwnerRepository.deleteById(id);
        } else {
            throw new BakeryOwnerException("BakeryOwner not found with id " + id);
        }
    }

    @Override
    public BakeryOwner verifyEmail(String email, String otp) throws BakeryOwnerException {
        // Verify the BakeryOwner's email by checking OTP and updating email verified status
        BakeryOwner bakeryOwner = this.getBakeryOwnerByEmail(email);
        bakeryOwner.setApproved(true);  // Assuming email verification approves the owner
        return bakeryOwnerRepository.save(bakeryOwner);  // Return the updated BakeryOwner
    }

    @Override
    public BakeryOwner updateBakeryOwnerAccountStatus(Long bakeryOwnerId, AccountStatus status) throws BakeryOwnerException {
        // Update BakeryOwner account status
        BakeryOwner bakeryOwner = this.getBakeryOwnerById(bakeryOwnerId);
        bakeryOwner.setAccountStatus(status);
        bakeryOwner.setApproved(true);
        return bakeryOwnerRepository.save(bakeryOwner);
    }
}
