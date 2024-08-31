package com.btg_pactual.application.usecases;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.services.ResetBalanceService;

public class ResetBalanceUsecase {
    private final ResetBalanceService service;

    public ResetBalanceUsecase(ResetBalanceService service) {
        this.service = service;
    }

    public ClientDTO execute(int clientId) {
        return service.resetBalance(clientId);
    }
}
