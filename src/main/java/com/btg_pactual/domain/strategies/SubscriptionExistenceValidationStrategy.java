package com.btg_pactual.domain.strategies;

import com.btg_pactual.domain.exceptions.SubscriptionAlreadyException;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.value_objects.Amount;

public class SubscriptionExistenceValidationStrategy implements ValidationStrategy {
    @Override
    public void canSubscribe(Client client, Fund fund, Amount amount) {
        if (client.isSubscribed(fund)) {
            throw new SubscriptionAlreadyException(fund.getName());
        }
    }
}

