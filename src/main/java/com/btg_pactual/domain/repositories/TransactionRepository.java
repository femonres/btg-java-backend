package com.btg_pactual.domain.repositories;

import java.util.List;

import com.btg_pactual.domain.model.Transaction;

public interface TransactionRepository {
    
    List<Transaction> getAllTransactionsByClientId(int clientId);

    void saveTransaction(Transaction transaction);
}
