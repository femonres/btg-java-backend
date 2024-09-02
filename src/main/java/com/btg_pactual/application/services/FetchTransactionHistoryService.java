package com.btg_pactual.application.services;

import java.util.List;
import java.util.stream.Collectors;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.mapper.TransactionMapper;
import com.btg_pactual.domain.services.TransactionService;

public class FetchTransactionHistoryService {
    private final TransactionService transactionService;

    public FetchTransactionHistoryService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public List<TransactionDTO> fetchTransactionHistory(int clientId) {
        return transactionService.getAllTransactionsByClient(clientId)
            .stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }
}
