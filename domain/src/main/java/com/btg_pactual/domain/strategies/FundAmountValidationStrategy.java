package com.btg_pactual.domain.strategies;

import com.btg_pactual.domain.exceptions.CanInvestException;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.value_objects.Amount;

public class FundAmountValidationStrategy implements ValidationStrategy {
    @Override
    public void canSubscribe(Client client, Fund fund, Amount amount) {
        if (!fund.canInvest(amount)) {
            throw new CanInvestException(fund.getName(), fund.getMinAmount());
        }
    }
}
