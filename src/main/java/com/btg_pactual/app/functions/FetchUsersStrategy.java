package com.btg_pactual.app.functions;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.usecases.FetchClientUsecase;
import com.btg_pactual.utils.ResponseUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchUsersStrategy implements RequestStrategy {

    private final FetchClientUsecase usecase;

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            List<ClientDTO> users = usecase.execute(null);
            return ResponseUtils.createSuccessfulResponse(users);
        } catch (Exception ex) {
            log.error("Error al listar los usuarios", ex);
            return ResponseUtils.createErrorResponse(ex.getMessage(), 400);
        }
    }

}
