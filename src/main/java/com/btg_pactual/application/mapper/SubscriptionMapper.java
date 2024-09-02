package com.btg_pactual.application.mapper;

import com.btg_pactual.application.dto.SubscriptionDTO;
import com.btg_pactual.domain.model.Subscription;

public class SubscriptionMapper {

    public static SubscriptionDTO toDTO(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setSubscriptionId(subscription.getSubscriptionId().getId());
        dto.setFundId(subscription.getFundId());
        dto.setFundName(subscription.getFundName());
        dto.setAmount(subscription.getAmount().getValue());
        return dto;
    }
}
