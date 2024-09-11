package com.btg_pactual.application.services;

import org.springframework.stereotype.Service;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.mapper.ClientMapper;
import com.btg_pactual.application.usecases.GetProfileUsecase;
import com.btg_pactual.domain.services.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetProfileService implements GetProfileUsecase {
    private final ClientService clientService;

    @Override
    public ClientDTO execute(Integer input) {
        return ClientMapper.toDTO(clientService.getClientById(input));
    }
}
