package com.btg_pactual.application.dto;

import java.time.LocalDateTime;

import com.btg_pactual.domain.enums.TransactionType;
import com.btg_pactual.domain.value_objects.Amount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    private String transactionId;
    private int fundId;
    private String fundName;
    private Amount amount;
    private LocalDateTime timestamp;
    private TransactionType transactionType;
}
