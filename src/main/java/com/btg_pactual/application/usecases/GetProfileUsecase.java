package com.btg_pactual.application.usecases;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.services.GetProfileService;

public class GetProfileUsecase {
    private final GetProfileService service;

    public GetProfileUsecase(GetProfileService service) {
        this.service = service;
    }

    public ClientDTO execute(int clientId) {
        return service.getProfile(clientId);
    }
}
