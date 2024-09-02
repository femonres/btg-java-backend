package com.btg_pactual.app.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.profile}")
    private String awsProfile;

    @Value("${aws.region}")
    private String awsRegion;

    DynamoDbClient createDynamoDbClient() {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create(awsProfile);

        return DynamoDbClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    DynamoDbClient createDynamoDbClientForTesting() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("fakeMyKeyId", "fakeSecretAccessKey");

        return DynamoDbClient.builder()
                .region(Region.of("fakeRegion"))
                .endpointOverride(URI.create("http://localhost:8000")) // DynamoDB Local o una URL de pruebas
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

}
