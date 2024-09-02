package com.btg_pactual.application.usecases;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.services.UpdateProfileService;

public class UpdateProfileUsecase {
    private final UpdateProfileService service;

    public UpdateProfileUsecase(UpdateProfileService service) {
        this.service = service;
    }

    public ClientDTO execute(int clientId, String name, String email, String phone, String notification) {
        return service.updateProfile(clientId, name, email, phone, notification);
    }
}
