package com.btg_pactual.application.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.mapper.TransactionMapper;
import com.btg_pactual.application.usecases.FetchTransactionHistoryUsecase;
import com.btg_pactual.domain.services.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchTransactionHistoryService implements FetchTransactionHistoryUsecase {
    private final TransactionService transactionService;

    @Override
    public List<TransactionDTO> execute(Integer input) {
        return transactionService.getAllTransactionsByClient(input)
            .stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }
}
