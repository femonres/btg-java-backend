package com.btg_pactual.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsubscribeFundDTO {

    @NotNull
    @Min(value = 1)
    private int userId;
}
