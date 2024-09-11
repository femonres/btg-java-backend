package com.btg_pactual.application.services;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.application.usecases.FetchClientUsecase;
import com.btg_pactual.domain.services.ClientService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchClientService implements FetchClientUsecase {
    private final ClientService clientService;

    @Override
    public List<ClientDTO> execute(Void input) {
        return clientService.getAllClients()
            .stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }
}
