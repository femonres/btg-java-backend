package com.btg_pactual.domain.strategies;

import com.btg_pactual.domain.exceptions.InsufficientBalanceException;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.value_objects.Amount;

public class BalanceValidationStrategy implements ValidationStrategy {
    @Override
    public void canSubscribe(Client client, Fund fund, Amount amount) {
        if (client.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(fund.getName());
        }
    }
}
