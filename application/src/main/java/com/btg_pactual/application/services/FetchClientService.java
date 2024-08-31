package com.btg_pactual.application.services;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.domain.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FetchClientService {
    private final ClientService clientService;

    public FetchClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<ClientDTO> fetchUsers() {
        return clientService.getAllClients()
            .stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }
}
