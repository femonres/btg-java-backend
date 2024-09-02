package com.btg_pactual.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeToFundDTO {
    private int clientId;
    private int fundId;
    private int amount;
}
