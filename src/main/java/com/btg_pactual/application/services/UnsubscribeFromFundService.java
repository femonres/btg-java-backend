package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.dto.UnsubscribeFundRequest;
import com.btg_pactual.application.mapper.TransactionMapper;
import com.btg_pactual.application.usecases.UnsubscribeFromFundUsecase;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.services.ClientService;
import com.btg_pactual.domain.services.FundService;
import com.btg_pactual.domain.services.NotificationService;
import com.btg_pactual.domain.services.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnsubscribeFromFundService implements UnsubscribeFromFundUsecase {
    private final ClientService clientService;
    private final FundService fundService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;

    @Override
    public TransactionDTO execute(UnsubscribeFundRequest input) {
        Client client = clientService.getClientById(input.getUserId());
        Fund fund = fundService.getFundById(input.getFundId());

        Transaction transaction = client.cancelFundSubscription(fund);
        transactionService.saveTransaction(transaction);
        clientService.saveClient(client);

        // Send notification
        notificationService.sendUnsubscriptionNotification(client, fund);
        return TransactionMapper.toDTO(transaction);
    }
}
