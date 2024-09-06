package com.btg_pactual.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeToFundDTO {
    @NotNull
    private int userId;
    
    @NotNull
    @Min(value = 0)
    private int amount;
}
