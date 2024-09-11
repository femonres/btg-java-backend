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
public class UnsubscribeFundRequest {

    @Schema(hidden = true)
    private int fundId;

    @NotNull
    @Min(value = 1)
    private int userId;
}
