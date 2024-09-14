package com.btg_pactual.app.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.profile:default}")
    private String awsProfile;

    @Value("${aws.region:us-west-2}")
    private String awsRegion;

    @Bean
    @Profile({"prod", "lambda"})
    DynamoDbClient createDynamoDbClient() {
        return DynamoDbClient.builder()
            .region(Region.of(awsRegion))
            .build();
    }
    
    @Bean
    @Profile("dev")
    DynamoDbClient createDynamoDbClientForTesting() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("fakeMyKeyId", "fakeSecretAccessKey");

        return DynamoDbClient.builder()
                .region(Region.of("fakeRegion"))
                .endpointOverride(URI.create("http://localhost:8000")) // DynamoDB Local o una URL de pruebas
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    @Bean
    DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

}
