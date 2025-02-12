package com.bakebuddy.service;

import java.util.List;

import com.bakebuddy.model.Order;
import com.bakebuddy.model.Seller;
import com.bakebuddy.model.Transaction;
import com.bakebuddy.model.User;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}
