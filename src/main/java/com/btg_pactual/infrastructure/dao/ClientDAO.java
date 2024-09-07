package com.btg_pactual.infrastructure.dao;

import java.util.Map;
import java.util.stream.Collectors;

import com.btg_pactual.domain.enums.NotificationType;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Subscription;
import com.btg_pactual.domain.value_objects.Amount;
import com.btg_pactual.domain.value_objects.Identifier;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ClientDAO {

    public static Map<String, AttributeValue> toDynamoItem(Client client) {
        return Map.of(
            "PK", AttributeValue.builder().s(String.format("CLIENT#%d", client.getId())).build(),
            "Name", AttributeValue.builder().s(client.getName()).build(),
            "Email", AttributeValue.builder().s(client.getEmail()).build(),
            "Phone", AttributeValue.builder().s(client.getPhone()).build(),
            "Balance", AttributeValue.builder().n(String.valueOf(client.getBalance().getValue())).build(),
            "Notification", AttributeValue.builder().s(client.getNotification().name().toLowerCase()).build(),
            "Subscriptions", AttributeValue.builder().l(
                client.getSubscriptions().stream()
                    .map(ClientDAO::toSubscriptionMap)
                    .map(attrMap -> AttributeValue.builder().m(attrMap).build())
                    .collect(Collectors.toList())
            ).build()
        );
    }

    private static Map<String, AttributeValue> toSubscriptionMap(Subscription subscription) {
        return Map.of(
            "ID", AttributeValue.builder().s(subscription.getSubscriptionId().getId()).build(),
            "FundID", AttributeValue.builder().s(String.valueOf(subscription.getFundId())).build(),
            "FundName", AttributeValue.builder().s(subscription.getFundName()).build(),
            "Amount", AttributeValue.builder().s(String.valueOf(subscription.getAmount().getValue())).build(),
            "Timestamp", AttributeValue.builder().s(subscription.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).build()
        );
    }

    public static Client fromDynamoItem(Map<String, AttributeValue> item) {
        Client client = new Client();
        client.setId(Integer.parseInt(item.get("PK").s().split("#")[1]));
        client.setName(item.get("Name").s());
        client.setEmail(item.get("Email").s());
        client.setPhone(item.get("Phone").s());
        client.setBalance(new Amount(Integer.parseInt(item.get("Balance").n())));
        client.setNotification(NotificationType.valueOf(item.get("Notification").s().toUpperCase()));
        if (item.get("Subscriptions") != null) {
            client.setSubscriptions(item.get("Subscriptions").l().stream()
                .map(attrValue -> fromSubscriptionMap(attrValue.m()))
                .collect(Collectors.toList()));
        }
        
        return client;
    }

    private static Subscription fromSubscriptionMap(Map<String, AttributeValue> map) {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(new Identifier(map.get("ID").s()));
        subscription.setFundId(Integer.parseInt(map.get("FundID").s()));
        subscription.setFundName(map.get("FundName").s());
        subscription.setAmount(new Amount(Integer.parseInt(map.get("Amount").s())));
        subscription.setTimestamp(LocalDateTime.parse(map.get("Timestamp").s(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return subscription;
    }
}