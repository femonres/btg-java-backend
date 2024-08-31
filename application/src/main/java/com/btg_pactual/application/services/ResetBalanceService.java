package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.services.ClientService;

@Service
public class ResetBalanceService {
    private final ClientService clientService;

    public ResetBalanceService(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientDTO resetBalance(int clientId) {
        Client client = clientService.getClientById(clientId);
        return ClientMapper.toDTO(clientService.resetBalance(client));
    }
}
