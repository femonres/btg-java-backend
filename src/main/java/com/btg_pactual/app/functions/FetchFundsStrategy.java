package com.btg_pactual.app.functions;

import java.util.List;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.application.dto.FundDTO;
import com.btg_pactual.application.usecases.FetchFundsUsecase;
import com.btg_pactual.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchFundsStrategy implements RequestStrategy {

    private final FetchFundsUsecase usecase;
    
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request) {
        try {
            List<FundDTO> funds = usecase.execute(null);
            return ResponseUtils.createSuccessfulResponse(funds);
        } catch (Exception e) {
            log.error("Error durante la obtención de fondos", e);
            return ResponseUtils.createErrorResponse(e.getMessage(), 400);
        }
    }
    
}
