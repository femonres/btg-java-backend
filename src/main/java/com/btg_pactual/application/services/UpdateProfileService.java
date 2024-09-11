package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.dto.UpdateProfileClientRequest;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.application.usecases.UpdateProfileUsecase;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.services.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProfileService implements UpdateProfileUsecase {
    private final ClientService clientService;
    
    @Override
    public ClientDTO execute(UpdateProfileClientRequest input) {
        Client client = clientService.getClientById(input.getUserId());

        client.setName(input.getName());
        client.setEmail(input.getEmail());
        client.setPhone(input.getPhone());
        client.setNotification(input.getNotification());

        // Update profile
        clientService.updateProfile(client);
        return ClientMapper.toDTO(client);
    }

}
