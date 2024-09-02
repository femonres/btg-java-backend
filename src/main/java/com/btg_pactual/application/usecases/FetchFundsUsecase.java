package com.btg_pactual.application.usecases;

import java.util.List;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.services.FetchFundsService;

public class FetchFundsUsecase {
    private final FetchFundsService service;

    public FetchFundsUsecase(FetchFundsService service) {
        this.service = service;
    }

    public List<FundDTO> execute() {
        return service.fetchFunds();
    }
}
