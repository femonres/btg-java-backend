package com.btg_pactual.app.controllers;

import org.springframework.web.bind.annotation.*;

import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.dto.SubscribeToFundRequest;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.dto.UnsubscribeFundRequest;
import com.btg_pactual.application.usecases.FetchFundsUsecase;
import com.btg_pactual.application.usecases.SubscribeToFundUsecase;
import com.btg_pactual.application.usecases.UnsubscribeFromFundUsecase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funds")
@Tag(name = "Funds", description = "Endpoints para gestionar fondos")
public class FundController {

    private final FetchFundsUsecase fetchFundsUsecase;
    private final SubscribeToFundUsecase subscribeToFundUsecase;
    private final UnsubscribeFromFundUsecase unsubscribeFromFundUsecase;

    @GetMapping
    @Operation(summary = "Listar todos los fondos")
    public List<FundDTO> fetchFunds() {
        return fetchFundsUsecase.execute(null)
            .stream()
            .sorted(Comparator.comparingInt(FundDTO::getId))
            .collect(Collectors.toList());
    }

    @PostMapping("/{fundId}/subscribe")
    @Operation(summary = "Suscribirse a un fondo por su ID")
    public TransactionDTO subscribeToFund(@PathVariable int fundId, @Valid @RequestBody SubscribeToFundRequest subscriptionDTO) {
        subscriptionDTO.setFundId(fundId);
        return subscribeToFundUsecase.execute(subscriptionDTO);
    }

    @PostMapping("/{fundId}/unsubscribe")
    @Operation(summary = "Darse de baja de un fondo por su ID")
    public TransactionDTO unsubscribeFromFund(@PathVariable int fundId, @Valid @RequestBody UnsubscribeFundRequest unsubscribeFundDTO) {
        unsubscribeFundDTO.setFundId(fundId);
        return unsubscribeFromFundUsecase.execute(unsubscribeFundDTO);
    }
}
