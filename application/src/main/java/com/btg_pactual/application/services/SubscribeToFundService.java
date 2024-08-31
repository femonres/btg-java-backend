package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.mapper.TransactionMapper;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.services.ClientService;
import com.btg_pactual.domain.services.FundService;
import com.btg_pactual.domain.services.NotificationService;
import com.btg_pactual.domain.services.TransactionService;
import com.btg_pactual.domain.value_objects.Amount;

@Service
public class SubscribeToFundService {
    private final ClientService clientService;
    private final FundService fundService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;

    public SubscribeToFundService(ClientService clientService, FundService fundService, TransactionService transactionService, NotificationService notificationService) {
        this.clientService = clientService;
        this.fundService = fundService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
    }

    public TransactionDTO subscribeToFund(int clientId, int fundId, Amount amount) {
        Client client = clientService.getClientById(clientId);
        Fund fund = fundService.getFundById(fundId);

        Transaction transaction = client.subscribeToFund(fund, amount);
        transactionService.saveTransaction(transaction);

        // Send notification
        notificationService.sendSubscriptionNotification(client, fund);

        return TransactionMapper.toDTO(transaction);
    }
}
