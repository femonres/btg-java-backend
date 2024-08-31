package com.btg_pactual.application.usecases;

import java.util.List;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.services.FetchClientService;

public class FetchClientUsecase {
    private final FetchClientService service;

    public FetchClientUsecase(FetchClientService service) {
        this.service = service;
    }

    public List<ClientDTO> execute() {
        return service.fetchUsers();
    }
}
