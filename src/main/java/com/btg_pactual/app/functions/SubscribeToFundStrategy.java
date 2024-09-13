package com.btg_pactual.app.functions;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.SubscribeToFundRequest;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.usecases.SubscribeToFundUsecase;
import com.btg_pactual.utils.ResponseUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscribeToFundStrategy implements RequestStrategy {

    private final SubscribeToFundUsecase usecase;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            SubscribeToFundRequest clientRequest = objectMapper.readValue(request.getBody(), SubscribeToFundRequest.class);
            clientRequest.setFundId(Integer.parseInt(request.getPath().split("/")[2]));
            TransactionDTO transaction = usecase.execute(clientRequest);
            return ResponseUtils.createSuccessfulResponse(transaction);
        } catch (Exception e) {
            log.error("Error al intentar suscribirse al fondo", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
