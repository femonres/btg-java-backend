package com.btg_pactual.application.usecases;

import java.util.List;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.services.FetchTransactionHistoryService;

public class FetchTransactionHistoryUsecase {
    private final FetchTransactionHistoryService service;

    public FetchTransactionHistoryUsecase(FetchTransactionHistoryService service) {
        this.service = service;
    }

    public List<TransactionDTO> execute(int clientId) {
        return service.fetchTransactionHistory(clientId);
    }

}
