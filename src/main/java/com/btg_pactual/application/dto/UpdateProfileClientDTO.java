package com.btg_pactual.application.dto;

import com.btg_pactual.domain.enums.NotificationType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileClientDTO {
    @NotBlank
    @Schema(required = true)
    private String name;

    @Email
    @NotBlank
    @Schema(required = true)
    private String email;
    
    @NotBlank
    @Schema(required = true)
    private String phone;

    @Schema(required = true, allowableValues = {"EMAIL", "PHONE"})
    private NotificationType notification;
}
