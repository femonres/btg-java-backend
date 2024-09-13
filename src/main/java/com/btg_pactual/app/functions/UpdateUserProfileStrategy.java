package com.btg_pactual.app.functions;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.dto.UpdateProfileClientRequest;
import com.btg_pactual.application.usecases.UpdateProfileUsecase;
import com.btg_pactual.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserProfileStrategy implements RequestStrategy {

    private final UpdateProfileUsecase usecase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            UpdateProfileClientRequest clientRequest = objectMapper.readValue(request.getBody(), UpdateProfileClientRequest.class);
            clientRequest.setUserId(Integer.parseInt(request.getPath().split("/")[2]));
            ClientDTO user = usecase.execute(clientRequest);
            return ResponseUtils.createSuccessfulResponse(user);
        } catch (Exception e) {
            log.error("Error durante la actualización del perfil del usuario", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
