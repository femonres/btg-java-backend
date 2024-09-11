package com.btg_pactual.app.config;

import java.util.List;
import java.util.Map;

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
    Map<String, Object> useCasesMap(
        FetchClientUsecase fetchClientUsecase,
        FetchTransactionHistoryUsecase fetchTransactionHistoryUsecase,
        GetProfileUsecase getProfileUsecase,
        UpdateProfileUsecase updateProfileUsecase,
        ResetBalanceUsecase resetBalanceUsecase,
        FetchFundsUsecase fetchFundsUsecase,
        SubscribeToFundUsecase subscribeToFundUsecase,
        UnsubscribeFromFundUsecase unsubscribeFromFundUsecase
    ) {
        return Map.of(
                "fetchClient", fetchClientUsecase,
                "fetchTransactionHistory", fetchTransactionHistoryUsecase,
                "getProfile", getProfileUsecase,
                "updateProfile", updateProfileUsecase,
                "resetBalance", resetBalanceUsecase,
                "fetchFunds", fetchFundsUsecase,
                "subscribeToFund", subscribeToFundUsecase,
                "unsubscribeFromFund", unsubscribeFromFundUsecase
        );
    }
    
    @Bean
    FetchClientUsecase gFetchClientUsecase(ClientService service) {
        return new FetchClientService(service);
    }

    @Bean
    FetchFundsUsecase gFetchFundsService(FundService service) {
        return new FetchFundsService(service);
    }

    @Bean
    FetchTransactionHistoryUsecase gFetchTransactionHistoryService(TransactionService service) {
        return new FetchTransactionHistoryService(service);
    }

    @Bean
    GetProfileUsecase gProfileService(ClientService service) {
        return new GetProfileService(service);
    }

    @Bean
    ResetBalanceUsecase gResetBalanceService(ClientService service) {
        return new ResetBalanceService(service);
    }

    @Bean
    SubscribeToFundUsecase gSubscribeToFundService(ClientService clientService, FundService fundService, TransactionService transactionService, NotificationService notificationService) {
        List<ValidationStrategy> strategies = List.of(
            new BalanceValidationStrategy(),
            new FundAmountValidationStrategy(),
            new SubscriptionExistenceValidationStrategy()
        );
        return new SubscribeToFundService(clientService, fundService, transactionService, notificationService, strategies);
    }

    @Bean
    UnsubscribeFromFundUsecase gUnsubscribeFromFundService(ClientService clientService, FundService fundService, TransactionService transactionService, NotificationService notificationService) {
        return new UnsubscribeFromFundService(clientService, fundService, transactionService, notificationService);
    }

    @Bean
    UpdateProfileUsecase gUpdateProfileService(ClientService service) {
        return new UpdateProfileService(service);
    }

}
