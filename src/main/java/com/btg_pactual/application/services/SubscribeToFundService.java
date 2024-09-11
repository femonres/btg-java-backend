package com.btg_pactual.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.SubscribeToFundRequest;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.mapper.TransactionMapper;
import com.btg_pactual.application.usecases.SubscribeToFundUsecase;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.services.ClientService;
import com.btg_pactual.domain.services.FundService;
import com.btg_pactual.domain.services.NotificationService;
import com.btg_pactual.domain.services.TransactionService;
import com.btg_pactual.domain.strategies.ValidationStrategy;
import com.btg_pactual.domain.value_objects.Amount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeToFundService implements SubscribeToFundUsecase {
    private final ClientService clientService;
    private final FundService fundService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;
    private final List<ValidationStrategy> validationStrategies;

    @Override
    public TransactionDTO execute(SubscribeToFundRequest input) {
        Client client = clientService.getClientById(input.getUserId());
        Fund fund = fundService.getFundById(input.getFundId());

        client.setValidations(validationStrategies);
        Transaction transaction = client.subscribeToFund(fund, new Amount(input.getAmount()));
        transactionService.saveTransaction(transaction);
        clientService.saveClient(client);

        // Send notification
        notificationService.sendSubscriptionNotification(client, fund);
        return TransactionMapper.toDTO(transaction);
    }
}
