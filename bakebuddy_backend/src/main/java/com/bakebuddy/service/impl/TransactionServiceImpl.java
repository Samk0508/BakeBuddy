package com.bakebuddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.Transaction;
import com.bakebuddy.repository.BakeryOwnerRepository;
import com.bakebuddy.repository.TransactionRepository;
import com.bakebuddy.service.TransactionService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	@Autowired
    private  TransactionRepository transactionRepository;
	@Autowired
	private  BakeryOwnerRepository bakeryOwnerRepository;

 
   

    @Override
    public Transaction createTransaction(Order order) {
    	BakeryOwner bakeryOwner = bakeryOwnerRepository.findById(order.getBakeryDetails().getId()).get();
        Transaction transaction = new Transaction();
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        transaction.setBakeryOwner(bakeryOwner);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByBakeryOwner(BakeryOwner bakeryOwner) {
        return transactionRepository.findByBakeryOwnerId(bakeryOwner.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

	

}
