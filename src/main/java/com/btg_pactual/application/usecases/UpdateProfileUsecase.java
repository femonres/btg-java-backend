package com.btg_pactual.application.usecases;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.dto.UpdateProfileClientDTO;
import com.btg_pactual.application.services.UpdateProfileService;

public class UpdateProfileUsecase {
    private final UpdateProfileService service;

    public UpdateProfileUsecase(UpdateProfileService service) {
        this.service = service;
    }

    public ClientDTO execute(int clientId, UpdateProfileClientDTO dto) {
        return service.updateProfile(clientId, dto.getName(), dto.getEmail(), dto.getPhone(), dto.getNotification());
    }
}
