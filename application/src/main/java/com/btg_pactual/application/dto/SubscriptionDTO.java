package com.btg_pactual.application.dto;

import com.btg_pactual.domain.value_objects.Amount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {
    private String subscriptionId;
    private int fundId;
    private String fundName;
    private Amount amount;
}
