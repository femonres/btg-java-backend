package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.application.usecases.ResetBalanceUsecase;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.services.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResetBalanceService implements ResetBalanceUsecase {
    private final ClientService clientService;

    @Override
    public ClientDTO execute(Integer input) {
        Client client = clientService.getClientById(input);
        return ClientMapper.toDTO(clientService.resetBalance(client));
    }
}
