package com.btg_pactual.domain.services;

import java.util.List;

import com.btg_pactual.domain.exceptions.SubscriptionNotFoundException;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.repositories.TransactionRepository;
import com.btg_pactual.domain.strategies.ValidationStrategy;
import com.btg_pactual.domain.value_objects.Amount;

public class SubscriptionService {
    private final TransactionRepository transactionRepository;
    private final List<ValidationStrategy> validationStrategies;

    public SubscriptionService(TransactionRepository transactionRepository, List<ValidationStrategy> validationStrategies) {
        this.transactionRepository = transactionRepository;
        this.validationStrategies = validationStrategies;
    }

    public Transaction subscribeToFund(Client client, Fund fund, Amount amount) {
        for (ValidationStrategy strategy : validationStrategies) {
            strategy.canSubscribe(client, fund, amount);
        }

        Transaction transaction = client.subscribeToFund(fund, amount);
        transactionRepository.saveTransaction(transaction);

        return transaction;
    }

    public Transaction unsubscribeFromFund(Client client, Fund fund) {
        if (!client.isSubscribed(fund)) {
            throw new SubscriptionNotFoundException(fund.getName());
        }

        Transaction transaction = client.cancelFundSubscription(fund);
        transactionRepository.saveTransaction(transaction);

        return transaction;
    }
}
