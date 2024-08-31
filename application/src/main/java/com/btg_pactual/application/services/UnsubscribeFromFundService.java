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

@Service
public class UnsubscribeFromFundService {
    private final ClientService clientService;
    private final FundService fundService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;

    public UnsubscribeFromFundService(ClientService clientService, FundService fundService,
            TransactionService transactionService, NotificationService notificationService) {
        this.clientService = clientService;
        this.fundService = fundService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
    }

    public TransactionDTO unsubscribeFromFund(int clientId, int fundId) {
        Client client = clientService.getClientById(clientId);
        Fund fund = fundService.getFundById(fundId);

        Transaction transaction = client.cancelFundSubscription(fund);
        transactionService.saveTransaction(transaction);

        // Send notification
        notificationService.sendUnsubscriptionNotification(client, fund);
        return TransactionMapper.toDTO(transaction);
    }
}
