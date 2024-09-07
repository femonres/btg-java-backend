package com.btg_pactual.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {
    private String subscriptionId;
    private int fundId;
    private String fundName;
    private int amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Schema(description = "Timestamp in ISO 8601 format", example = "2024-09-05T18:09:56.289Z")
    private String timestamp;
}
