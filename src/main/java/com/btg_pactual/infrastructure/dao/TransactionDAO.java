package com.btg_pactual.infrastructure.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.btg_pactual.domain.enums.TransactionType;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.model.Transaction;
import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.FundCategory;
import com.btg_pactual.domain.value_objects.Identifier;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class TransactionDAO {
    public static Map<String, AttributeValue> toDynamoItem(Transaction transaction) {
        return Map.of(
            "PK", AttributeValue.builder().s(String.valueOf(transaction.getTransactionId().getId())).build(),
            "ClientID", AttributeValue.builder().s(String.valueOf(transaction.getClientId())).build(),
            "FundID", AttributeValue.builder().s(String.valueOf(transaction.getFund().getId())).build(),
            "FundName", AttributeValue.builder().s(transaction.getFund().getName()).build(),
            "Amount", AttributeValue.builder().n(String.valueOf(transaction.getAmount().getValue())).build(),
            "TransactionType", AttributeValue.builder().s(transaction.getTransactionType().name().toLowerCase()).build(),
            "Timestamp", AttributeValue.builder().s(transaction.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).build()
        );
    }

    public static Transaction fromDynamoItem(Map<String, AttributeValue> item) {
        Fund fund = new Fund(
            Integer.parseInt(item.get("FundID").s()),
            item.get("FundName").s(),
            new Amount(0),
            new FundCategory("FPV")
        );
        Transaction transaction = new Transaction();
        transaction.setTransactionId(new Identifier(item.get("PK").s()));
        transaction.setClientId(Integer.parseInt(item.get("ClientID").s()));
        transaction.setFund(fund);
        transaction.setAmount(new Amount(Integer.parseInt(item.get("Amount").n())));
        transaction.setTransactionType(TransactionType.valueOf(item.get("TransactionType").s().toUpperCase()));
        transaction.setTimestamp(LocalDateTime.parse(item.get("Timestamp").s(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return transaction;
    }
}
