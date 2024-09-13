package com.btg_pactual.app.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class RequestStrategyMapping {

    private final Map<Pattern, RequestStrategy> strategyMap = new HashMap<>();

    public RequestStrategyMapping(FetchUsersStrategy fetchUsersStrategy,
            GetUserByIdStrategy getUserByIdStrategy,
            UpdateUserProfileStrategy updateUserProfileStrategy,
            ResetBalanceOfUserStrategy resetBalanceOfUserStrategy,
            FetchTransactionsOfUserStrategy fetchTransactionsOfUserStrategy,
            FetchFundsStrategy fetchFundsStrategy,
            SubscribeToFundStrategy subscribeToFundStrategy,
            UnsubscribeFromFundStrategy unsubscribeFromFundStrategy) {

        strategyMap.put(Pattern.compile("^GET/users$"), fetchUsersStrategy);
        strategyMap.put(Pattern.compile("^GET/users/\\d+$"), getUserByIdStrategy);
        strategyMap.put(Pattern.compile("^PUT/users/\\d+$"), updateUserProfileStrategy);
        strategyMap.put(Pattern.compile("^PUT/users/\\d+/reset$"), resetBalanceOfUserStrategy);
        strategyMap.put(Pattern.compile("^GET/users/\\d+/transacctions$"), fetchTransactionsOfUserStrategy);

        strategyMap.put(Pattern.compile("^GET/funds$"), fetchFundsStrategy);
        strategyMap.put(Pattern.compile("^POST/funds/\\d+/subscribe$"), subscribeToFundStrategy);
        strategyMap.put(Pattern.compile("^POST/funds/\\d+/unsubscribe$"), unsubscribeFromFundStrategy);

    }

    public RequestStrategy getStrategy(String httpMethod, String path) {
        if (path == null) {
            return null;
        }
        
        return strategyMap.entrySet().stream()
                .filter(entry -> entry.getKey().matcher(String.format("%s%s", httpMethod, path)).matches())
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }
}
