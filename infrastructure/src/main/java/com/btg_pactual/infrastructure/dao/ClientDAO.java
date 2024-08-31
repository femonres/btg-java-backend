package com.btg_pactual.infrastructure.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.btg_pactual.domain.enums.NotificationType;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.value_objects.Amount;

public class ClientDAO {
    public static Item toDynamoItem(Client client) {
        return new Item()
            .withPrimaryKey("PK", "CLIENT#" + client.getId())
            .withString("Name", client.getName())
            .withString("Email", client.getEmail())
            .withString("Phone", client.getPhone())
            .withString("NotificationPreference", client.getNotification().toString())
            .withNumber("Balance", client.getBalance().getValue());
    }

    public static Client fromDynamoItem(Item item) {
        return new Client(
            Integer.parseInt(item.getString("PK").split("#")[1]),
            item.getString("Name"),
            item.getString("Email"),
            item.getString("Phone"),
            new Amount(item.getInt("Balance")),
            NotificationType.valueOf(item.getString("NotificationPreference"))
        );
    }
}
