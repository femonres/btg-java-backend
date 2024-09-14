package com.btg_pactual.application.dto;

import com.btg_pactual.domain.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateProfileClientRequest {

    @Schema(hidden = true)
    private int userId;
    
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String phone;

    @Schema(allowableValues = {"EMAIL", "PHONE"})
    private NotificationType notification;
}
