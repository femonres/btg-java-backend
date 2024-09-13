package com.btg_pactual.app.functions;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public interface RequestStrategy {

    APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent request);
}
