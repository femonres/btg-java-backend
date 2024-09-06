package com.btg_pactual.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.btg_pactual.domain.repositories.ClientRepository;
import com.btg_pactual.domain.repositories.FundRepository;
import com.btg_pactual.domain.repositories.TransactionRepository;
import com.btg_pactual.domain.services.ClientService;
import com.btg_pactual.domain.services.FundService;
import com.btg_pactual.domain.services.NotificationService;
import com.btg_pactual.domain.services.TransactionService;
import com.btg_pactual.infrastructure.messaging.SNSNotificationServiceImpl;
import com.btg_pactual.infrastructure.persistence.repositories.DynamoDBClientRepositoryImpl;
import com.btg_pactual.infrastructure.persistence.repositories.DynamoDBFundRepositoryImpl;
import com.btg_pactual.infrastructure.persistence.repositories.DynamoDBTransactionRepositoryImpl;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class DomainLayerConfig {
    
    @Bean
    ClientRepository clientRepository(DynamoDbClient dbClient) {
        return new DynamoDBClientRepositoryImpl(dbClient);
    }

    @Bean
    FundRepository fundRepository(DynamoDbClient dbClient) {
        return new DynamoDBFundRepositoryImpl(dbClient);
    }

    @Bean
    TransactionRepository transactionRepository(DynamoDbClient dbClient) {
        return new DynamoDBTransactionRepositoryImpl(dbClient);
    }

    // Domain Service

    @Bean
    FundService getFundService(FundRepository repository) {
        return new FundService(repository);
    }

    @Bean
    ClientService getClientService(ClientRepository repository) {
        return new ClientService(repository);
    }

    @Bean
    TransactionService getTransactionService(TransactionRepository repository) {
        return new TransactionService(repository);
    }

    @Bean
    NotificationService getNotificationService(SnsClient client) {
        return new SNSNotificationServiceImpl(client);
    }

}
