package com.btg_pactual.app.functions;

import java.util.List;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.usecases.FetchTransactionHistoryUsecase;
import com.btg_pactual.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchTransactionsOfUserStrategy implements RequestStrategy {

    private final FetchTransactionHistoryUsecase usecase;

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            String userId = request.getPath().split("/")[2];
            List<TransactionDTO> transactions = usecase.execute(Integer.valueOf(userId));
            return ResponseUtils.createSuccessfulResponse(transactions);
        } catch (Exception e) {
            log.error("Error al obtener las transacciones del usuario", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
