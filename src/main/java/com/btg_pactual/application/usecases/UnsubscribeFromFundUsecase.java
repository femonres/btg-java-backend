package com.btg_pactual.application.usecases;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.services.UnsubscribeFromFundService;

public class UnsubscribeFromFundUsecase {
    private final UnsubscribeFromFundService service;

    public UnsubscribeFromFundUsecase(UnsubscribeFromFundService service) {
        this.service = service;
    }

    public TransactionDTO execute(int clientId, int fundId) {
        return service.unsubscribeFromFund(clientId, fundId);
    }
}
