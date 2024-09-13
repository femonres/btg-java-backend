package com.btg_pactual.app.functions;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.usecases.GetProfileUsecase;
import com.btg_pactual.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserByIdStrategy implements RequestStrategy {

    private final GetProfileUsecase usecase;

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            String userId = request.getPath().split("/")[2];
            ClientDTO user = usecase.execute(Integer.valueOf(userId));
            log.debug("Usuario obtenido: {}", user);
            return ResponseUtils.createSuccessfulResponse(user);
        } catch (Exception e) {
            log.error("Error al obtener el usuario", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
