package com.btg_pactual.domain.strategies;

import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.value_objects.Amount;

public interface ValidationStrategy {
    void canSubscribe(Client client, Fund fund, Amount amount);
}
