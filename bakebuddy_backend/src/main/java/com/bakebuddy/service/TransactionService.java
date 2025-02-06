package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.Transaction;


public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionByBakeryOwner(BakeryOwner bakeryOwner);
    List<Transaction>getAllTransactions();
}
