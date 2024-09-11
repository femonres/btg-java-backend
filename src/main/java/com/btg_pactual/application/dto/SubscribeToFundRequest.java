package com.btg_pactual.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class SubscribeToFundRequest {

    @Schema(hidden = true)
    private int fundId;

    @NotNull
    private int userId;
    
    @NotNull
    @Min(value = 0)
    private int amount;
}
