package com.btg_pactual.app.controllers;

import org.springframework.web.bind.annotation.*;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.dto.SubscribeToFundDTO;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.dto.UnsubscribeFundDTO;
import com.btg_pactual.application.usecases.FetchFundsUsecase;
import com.btg_pactual.application.usecases.SubscribeToFundUsecase;
import com.btg_pactual.application.usecases.UnsubscribeFromFundUsecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class FundController {

    private final FetchFundsUsecase fetchFundsUsecase;
    private final SubscribeToFundUsecase subscribeToFundUsecase;
    private final UnsubscribeFromFundUsecase unsubscribeFromFundUsecase;

    @GetMapping
    public List<FundDTO> fetchFunds() {
        return fetchFundsUsecase.execute()
        .stream()
        .sorted(Comparator.comparingInt(FundDTO::getId))
        .collect(Collectors.toList());
    }

    @PostMapping("/{fundId}/subscribe")
    public TransactionDTO subscribeToFund(@PathVariable int fundId, @Valid @RequestBody SubscribeToFundDTO subscriptionDTO) {
        return subscribeToFundUsecase.execute(fundId, subscriptionDTO);
    }

    @PostMapping("/{fundId}/unsubscribe")
    public TransactionDTO unsubscribeFromFund(@PathVariable int fundId, @Valid @RequestBody UnsubscribeFundDTO dto) {
        return unsubscribeFromFundUsecase.execute(dto.getUserId(), fundId);
    }
}
