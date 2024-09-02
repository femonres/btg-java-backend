package com.btg_pactual.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class MessagingConfig {

    @Value("${aws.profile}")
    private String awsProfile;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    SnsClient snsClient() {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create(awsProfile);

        return SnsClient.builder()
            .region(Region.of(awsRegion))
            .credentialsProvider(credentialsProvider)
            .build();
    }
}
