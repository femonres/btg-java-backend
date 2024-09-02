package com.btg_pactual.application.services;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.domain.enums.NotificationType;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.services.ClientService;

public class UpdateProfileService {
    private final ClientService clientService;

    public UpdateProfileService(ClientService clientService) {
        this.clientService = clientService;
    }
    
    public ClientDTO updateProfile(int clientId, String name, String email, String phone, String notification) {
        Client client = clientService.getClientById(clientId);

        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setNotification(NotificationType.valueOf(notification));

        // Update profile
        clientService.updateProfile(client);
        return ClientMapper.toDTO(client);
    }

}
