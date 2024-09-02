package com.btg_pactual.domain.services;

import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;

public interface NotificationService {
    
    void sendSubscriptionNotification(Client client, Fund fund);

    void sendUnsubscriptionNotification(Client client, Fund fund);
}
