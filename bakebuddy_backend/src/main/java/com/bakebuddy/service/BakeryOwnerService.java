package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.enums.AccountStatus;
import com.bakebuddy.exception.BakeryOwnerException;

public interface BakeryOwnerService {
	
	BakeryOwner createBakeryOwner(BakeryOwner bakeryOwner)throws BakeryOwnerException;
	BakeryOwner getBakeryOwnerById(Long id)throws BakeryOwnerException;
	BakeryOwner getBakeryOwnerByEmail(String email)throws BakeryOwnerException;
    List<BakeryOwner> getAllBakeryOwner(AccountStatus status);
    BakeryOwner updateBakeryOwner(Long id, BakeryOwner bakeryOwner)throws BakeryOwnerException;
    void deleteBakeryOwner(Long id)throws BakeryOwnerException;
    BakeryOwner verifyEmail(String email,String otp)throws BakeryOwnerException;

    BakeryOwner updateBakeryOwnerAccountStatus(Long bakeryOwnerId, AccountStatus status)throws BakeryOwnerException;
	BakeryOwner getBakeryOwnerProfile(String jwt)throws BakeryOwnerException ;
}
