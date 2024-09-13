package com.btg_pactual.utils;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.app.responses.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ResponseUtils {
    private ResponseUtils() {} // Private constructor to prevent instantiation
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static APIGatewayProxyResponseEvent createSuccessfulResponse(Object data) throws JsonProcessingException {
        return new APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withBody(objectMapper.writeValueAsString(data))
            .withHeaders(Map.of("Content-Type", "application/json", "Access-Control-Allow-Origin", "*"));
    }

    public static APIGatewayProxyResponseEvent createErrorResponse(String message, int statusCode) {
        ErrorResponse errorResponse = new ErrorResponse(message, statusCode);
        String body;
        try {
            body = objectMapper.writeValueAsString(errorResponse);
        } catch (Exception e) {
            body = "{\"message\":\"Internal Server Error\",\"status\":500}";
        }

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(body)
                .withHeaders(Map.of("Content-Type", "application/json", "Access-Control-Allow-Origin", "*"));
    }
}
