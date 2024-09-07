package com.btg_pactual.application.services;

import java.util.List;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.mapper.TransactionMapper;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.services.ClientService;
import com.btg_pactual.domain.services.FundService;
import com.btg_pactual.domain.services.NotificationService;
import com.btg_pactual.domain.services.TransactionService;
import com.btg_pactual.domain.strategies.ValidationStrategy;
import com.btg_pactual.domain.value_objects.Amount;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubscribeToFundService {
    private final ClientService clientService;
    private final FundService fundService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;
    private final List<ValidationStrategy> validationStrategies;

    public SubscribeToFundService(ClientService clientService, FundService fundService, TransactionService transactionService, NotificationService notificationService, List<ValidationStrategy> validationStrategies) {
        this.clientService = clientService;
        this.fundService = fundService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
        this.validationStrategies = validationStrategies;
    }

    public TransactionDTO subscribeToFund(int fundId, int clientId, int amount) {
        Client client = clientService.getClientById(clientId);
        Fund fund = fundService.getFundById(fundId);

        client.setValidations(validationStrategies);
        Transaction transaction = client.subscribeToFund(fund, new Amount(amount));
        transactionService.saveTransaction(transaction);
        clientService.saveClient(client);

        // Send notification
        notificationService.sendSubscriptionNotification(client, fund);
        return TransactionMapper.toDTO(transaction);
    }
}
