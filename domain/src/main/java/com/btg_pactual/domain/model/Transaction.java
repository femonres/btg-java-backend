package com.btg_pactual.domain.model;

import java.time.LocalDateTime;

import com.btg_pactual.domain.enums.TransactionType;
import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.Identifier;

import lombok.Data;

@Data
public class Transaction {
    private Identifier transactionId = Identifier.generate();
    private int clientId;
    private Fund fund;
    private Amount amount;
    private LocalDateTime timestamp = LocalDateTime.now();
    private TransactionType transactionType;

    public Transaction(int clientId, Fund fund, Amount amount, TransactionType transactionType) {
        this.clientId = clientId;
        this.fund = fund;
        this.amount = amount;
        this.transactionType = transactionType;
    }
}
