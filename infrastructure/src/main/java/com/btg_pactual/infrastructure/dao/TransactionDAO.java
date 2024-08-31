package com.btg_pactual.infrastructure.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.btg_pactual.domain.enums.TransactionType;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.value_objects.Amount;

public class TransactionDAO {
    public static Item toDynamoItem(Transaction transaction) {
        return new Item()
            .withPrimaryKey("PK", String.valueOf(transaction.getTransactionId().getId()))
            .withString("ClientID", String.valueOf(transaction.getClientId()))
            .withString("FundID", String.valueOf(transaction.getFund().getId()))
            .withNumber("Amount", transaction.getAmount().getValue())
            .withString("Type", transaction.getTransactionType().toString())
            .withString("Timestamp", transaction.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    public static Transaction fromDynamoItem(Item item) {
        return new Transaction(
            Integer.parseInt(item.getString("PK")),
            Integer.parseInt(item.getString("ClientID")),
            Integer.parseInt(item.getString("FundID")),
            new Amount(item.getInt("Amount")),
            TransactionType.valueOf(item.getString("Type")),
            LocalDateTime.parse(item.getString("Timestamp"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }
}
