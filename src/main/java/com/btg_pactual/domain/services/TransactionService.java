package com.btg_pactual.domain.services;

import java.util.List;

import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.repositories.TransactionRepository;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactionsByClient(int clientId) {
        return transactionRepository.getAllTransactionsByClientId(clientId);
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.saveTransaction(transaction);
    }
}
