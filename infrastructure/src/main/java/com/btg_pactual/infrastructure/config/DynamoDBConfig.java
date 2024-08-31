package com.btg_pactual.infrastructure.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDBConfig createDynamoDbClient() {
        String awsProfile = System.getenv("AWS_PROFILE");
        String awsRegion = System.getenv("AWS_REGION");

        InstanceProfileCredentialsProvider credentials = InstanceProfileCredentialsProvider.createAsyncRefreshingProvider(true);
        AmazonDynamoD
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create(awsProfile);

        return DynamoDbClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public DynamoDbClient createDynamoDbClientForTesting() {\
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("fakeMyKeyId", "fakeSecretAccessKey");

        return DynamoDbClient.builder()
                .region(Region.of("fakeRegion"))
                .endpointOverride(URI.create("http://localhost:8000")) // DynamoDB Local o una URL de pruebas
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

}
