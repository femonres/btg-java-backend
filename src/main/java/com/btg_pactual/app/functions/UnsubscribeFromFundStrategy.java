package com.btg_pactual.app.functions;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.dto.UnsubscribeFundRequest;
import com.btg_pactual.application.usecases.UnsubscribeFromFundUsecase;
import com.btg_pactual.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UnsubscribeFromFundStrategy implements RequestStrategy {

    private final UnsubscribeFromFundUsecase usecase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            UnsubscribeFundRequest unsubscribeFundRequest = objectMapper.readValue(request.getBody(), UnsubscribeFundRequest.class);
            unsubscribeFundRequest.setFundId(Integer.parseInt(request.getPath().split("/")[2]));
            TransactionDTO transaction = usecase.execute(unsubscribeFundRequest);
            return ResponseUtils.createSuccessfulResponse(transaction);
        } catch (Exception e) {
            log.error("Error al intentar dar de baja al usuario del fondo", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
