package com.btg_pactual.application.dto;

import com.btg_pactual.domain.value_objects.Amount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeToFundDTO {
    private int clientId;
    private int fundId;
    private Amount amount;
}
