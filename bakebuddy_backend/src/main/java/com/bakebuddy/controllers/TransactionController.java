package com.bakebuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.Transaction;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	@Autowired
    private TransactionService transactionService;
	@Autowired
	private BakeryOwnerService bakeryOwnerService;

   
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Order order) {
        Transaction transaction = transactionService.createTransaction(order);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionByBakeryOwner(
            @RequestHeader("Authorization") String jwt) throws BakeryOwnerException {
    	BakeryOwner bakeryOwner=bakeryOwnerService.getBakeryOwnerProfile(jwt);

        List<Transaction> transactions = transactionService.getTransactionByBakeryOwner(bakeryOwner);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
