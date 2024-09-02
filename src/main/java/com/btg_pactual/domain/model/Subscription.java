package com.btg_pactual.domain.model;

import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.Identifier;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Subscription {
    private Identifier subscriptionId = Identifier.generate();
    private int fundId;
    private String fundName;
    private Amount amount;

    public Subscription(int fundId, String fundName, Amount amount) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.amount = amount;
    }
}
