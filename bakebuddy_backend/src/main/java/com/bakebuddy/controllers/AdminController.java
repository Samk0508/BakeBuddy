package com.bakebuddy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.enums.AccountStatus;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.service.BakeryOwnerService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private BakeryOwnerService bakeryOwnerService;
  
    @PatchMapping("/Admin/{id}/status/{status}")
    public ResponseEntity<BakeryOwner> updateBakeryOwnerAccountStatus(
            @PathVariable Long id,
            @PathVariable AccountStatus status) throws BakeryOwnerException {

    	BakeryOwner updatedBakeryOwner = bakeryOwnerService.updateBakeryOwnerAccountStatus(id,status);
        return ResponseEntity.ok(updatedBakeryOwner);

    }

}
