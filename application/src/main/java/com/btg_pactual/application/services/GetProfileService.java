package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.domain.services.ClientService;

@Service
public class GetProfileService {
    private final ClientService clientService;

    public GetProfileService(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientDTO getProfile(int clientId) {
        return ClientMapper.toDTO(clientService.getClientById(clientId));
    }
}
