package com.btg_pactual.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.btg_pactual.application.services.FetchClientService;
import com.btg_pactual.application.services.FetchFundsService;
import com.btg_pactual.application.services.FetchTransactionHistoryService;
import com.btg_pactual.application.services.GetProfileService;
import com.btg_pactual.application.services.ResetBalanceService;
import com.btg_pactual.application.services.SubscribeToFundService;
import com.btg_pactual.application.services.UnsubscribeFromFundService;
import com.btg_pactual.application.services.UpdateProfileService;
import com.btg_pactual.application.usecases.FetchClientUsecase;
import com.btg_pactual.application.usecases.FetchFundsUsecase;
import com.btg_pactual.application.usecases.FetchTransactionHistoryUsecase;
import com.btg_pactual.application.usecases.GetProfileUsecase;
import com.btg_pactual.application.usecases.ResetBalanceUsecase;
import com.btg_pactual.application.usecases.SubscribeToFundUsecase;
import com.btg_pactual.application.usecases.UnsubscribeFromFundUsecase;
import com.btg_pactual.application.usecases.UpdateProfileUsecase;
import com.btg_pactual.domain.services.ClientService;
import com.btg_pactual.domain.services.FundService;
import com.btg_pactual.domain.services.NotificationService;
import com.btg_pactual.domain.services.TransactionService;
import com.btg_pactual.domain.strategies.BalanceValidationStrategy;
import com.btg_pactual.domain.strategies.FundAmountValidationStrategy;
import com.btg_pactual.domain.strategies.SubscriptionExistenceValidationStrategy;
import com.btg_pactual.domain.strategies.ValidationStrategy;

@Configuration
public class ApplicationLayerConfig {
    
    @Bean
    FetchClientService gFetchClientService(ClientService service) {
        return new FetchClientService(service);
    }

    @Bean
    FetchFundsService gFetchFundsService(FundService service) {
        return new FetchFundsService(service);
    }

    @Bean
    FetchTransactionHistoryService gFetchTransactionHistoryService(TransactionService service) {
        return new FetchTransactionHistoryService(service);
    }

    @Bean
    GetProfileService gProfileService(ClientService service) {
        return new GetProfileService(service);
    }

    @Bean
    ResetBalanceService gResetBalanceService(ClientService service) {
        return new ResetBalanceService(service);
    }

    @Bean
    SubscribeToFundService gSubscribeToFundService(ClientService clientService, FundService fundService, TransactionService transactionService, NotificationService notificationService) {
        List<ValidationStrategy> strategies = List.of(
            new BalanceValidationStrategy(),
            new FundAmountValidationStrategy(),
            new SubscriptionExistenceValidationStrategy()
        );
        return new SubscribeToFundService(clientService, fundService, transactionService, notificationService, strategies);
    }

    @Bean
    UnsubscribeFromFundService gUnsubscribeFromFundService(ClientService clientService, FundService fundService, TransactionService transactionService, NotificationService notificationService) {
        return new UnsubscribeFromFundService(clientService, fundService, transactionService, notificationService);
    }

    @Bean
    UpdateProfileService gUpdateProfileService(ClientService service) {
        return new UpdateProfileService(service);
    }

    // Usecases

    @Bean
    FetchClientUsecase gFetchClientUsecase(FetchClientService service) {
        return new FetchClientUsecase(service);
    }

    @Bean
    FetchFundsUsecase gFetchFundsUsecase(FetchFundsService service) {
        return new FetchFundsUsecase(service);
    }

    @Bean
    FetchTransactionHistoryUsecase gFetchTransactionHistoryUsecase(FetchTransactionHistoryService service) {
        return new FetchTransactionHistoryUsecase(service);
    }

    @Bean
    GetProfileUsecase gGetProfileUsecase(GetProfileService service) {
        return new GetProfileUsecase(service);
    }

    @Bean
    ResetBalanceUsecase gResetBalanceUsecase(ResetBalanceService service) {
        return new ResetBalanceUsecase(service);
    }

    @Bean
    SubscribeToFundUsecase gSubscribeToFundUsecase(SubscribeToFundService service) {
        return new SubscribeToFundUsecase(service);
    }

    @Bean
    UnsubscribeFromFundUsecase gUnsubscribeFromFundUsecase(UnsubscribeFromFundService service) {
        return new UnsubscribeFromFundUsecase(service);
    }

    @Bean
    UpdateProfileUsecase gUpdateProfileUsecase(UpdateProfileService service) {
        return new UpdateProfileUsecase(service);
    }

    // Services
}
