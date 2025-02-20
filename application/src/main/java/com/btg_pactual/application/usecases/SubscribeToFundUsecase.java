package com.btg_pactual.application.usecases;

import com.btg_pactual.application.dto.SubscribeToFundDTO;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.services.SubscribeToFundService;

public class SubscribeToFundUsecase {
    private final SubscribeToFundService subscriptionService;

    public SubscribeToFundUsecase(SubscribeToFundService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public TransactionDTO execute(SubscribeToFundDTO dto) {
        return subscriptionService.subscribeToFund(dto.getClientId(), dto.getFundId(), dto.getAmount());
    }
}
