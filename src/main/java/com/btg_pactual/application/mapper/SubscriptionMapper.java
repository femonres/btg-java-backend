package com.btg_pactual.application.mapper;

import java.time.format.DateTimeFormatter;

import com.btg_pactual.application.dto.SubscriptionDTO;
import com.btg_pactual.domain.model.Subscription;

public class SubscriptionMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public static SubscriptionDTO toDTO(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setSubscriptionId(subscription.getSubscriptionId().getId());
        dto.setFundId(subscription.getFundId());
        dto.setFundName(subscription.getFundName());
        dto.setAmount(subscription.getAmount().getValue());
        dto.setTimestamp(FORMATTER.format(subscription.getTimestamp()));
        return dto;
    }
}
