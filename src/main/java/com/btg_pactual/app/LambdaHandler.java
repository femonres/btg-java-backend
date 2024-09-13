package com.btg_pactual.app;

import java.util.Map;

import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.btg_pactual.app.functions.RequestStrategy;
import com.btg_pactual.app.functions.RequestStrategyFactory;
import com.btg_pactual.utils.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private ConfigurableApplicationContext applicationContext;
    private RequestStrategyFactory serviceFactory;
    
    public LambdaHandler() {
        if (applicationContext == null) {
            applicationContext = new SpringApplicationBuilder(Application.class)
                .initializers(new LambdaContextInitializer())
                .bannerMode(Banner.Mode.OFF)
                .run();
        }
        serviceFactory = applicationContext.getBean(RequestStrategyFactory.class);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String httpMethod = input.getHttpMethod();
        String path = input.getPath();
        Map<String, String> pathParameters = input.getPathParameters();

        log.info("Received HTTP method: {}", httpMethod);
        log.info("Received path: {}", path);
        log.info("Received path parameters: {}", pathParameters);
        log.info("Received body: {}", input.getBody());

        try {
            RequestStrategy strategy = serviceFactory.getStrategy(httpMethod, path);
            return strategy.handle(input);
        } catch (Exception e) {
            return ResponseUtils.createErrorResponse("Internal Server Error", 500);
        }
    }
}