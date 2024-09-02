package com.btg_pactual.app.controllers;

import org.springframework.web.bind.annotation.*;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.dto.SubscribeToFundDTO;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.usecases.FetchFundsUsecase;
import com.btg_pactual.application.usecases.SubscribeToFundUsecase;
import com.btg_pactual.application.usecases.UnsubscribeFromFundUsecase;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class FundController {

    private final FetchFundsUsecase fetchFundsUsecase;
    private final SubscribeToFundUsecase subscribeToFundUsecase;
    private final UnsubscribeFromFundUsecase unsubscribeFromFundUsecase;

    @GetMapping
    public List<FundDTO> getFunds() {
        return fetchFundsUsecase.execute();
    }

    @PostMapping("/{fund_id}/subscribe")
    public TransactionDTO subscribeToFund(@PathVariable("fund_id") int fundId, @RequestBody SubscribeToFundDTO subscriptionDTO) {
        return subscribeToFundUsecase.execute(subscriptionDTO);
    }

    @PostMapping("/{fund_id}/unsubscribe")
    public TransactionDTO unsubscribeFromFund(@PathVariable("fund_id") int fundId, @RequestBody SubscribeToFundDTO dto) {
        return unsubscribeFromFundUsecase.execute(fundId, dto.getClientId());
    }
}
