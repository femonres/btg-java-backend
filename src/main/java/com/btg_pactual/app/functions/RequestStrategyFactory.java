package com.btg_pactual.app.functions;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestStrategyFactory {
    
    private final RequestStrategyMapping strategyMapping;

    public RequestStrategy getStrategy(String httpMethod, String path) {
        RequestStrategy strategy = strategyMapping.getStrategy(httpMethod, path);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for path: " + path);
        }
        return strategy;
    }
}
