package com.paypal.Transaction.service;

import com.paypal.Transaction.dto.TransferRequest;
import com.paypal.Transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {

    Transaction createTransaction(Transaction transaction);
    public Transaction getTransactionById(Long id);

    public List<Transaction> getTransactionsByUser(Long userId);
}