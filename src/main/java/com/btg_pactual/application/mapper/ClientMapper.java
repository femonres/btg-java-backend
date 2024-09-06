package com.btg_pactual.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.dto.SubscriptionDTO;
import com.btg_pactual.domain.model.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setNotification(client.getNotification());
        dto.setBalance(client.getBalance().getValue());

        List<SubscriptionDTO> subscriptionDTOs = client.getSubscriptions().stream()
            .map(SubscriptionMapper::toDTO)
            .collect(Collectors.toList());
        dto.setSubscriptions(subscriptionDTOs);

        return dto;
    }
}
