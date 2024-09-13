package com.btg_pactual.app.functions;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.usecases.ResetBalanceUsecase;
import com.btg_pactual.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResetBalanceOfUserStrategy implements RequestStrategy {

    private final ResetBalanceUsecase usecase;

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            String userId = request.getPath().split("/")[2];
            ClientDTO user = usecase.execute(Integer.valueOf(userId));
            return ResponseUtils.createSuccessfulResponse(user);
        } catch (Exception e) {
            log.error("Error durante el reinicio del balance del usuario", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
