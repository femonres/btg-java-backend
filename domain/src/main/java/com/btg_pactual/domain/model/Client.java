package com.btg_pactual.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.btg_pactual.domain.enums.NotificationType;
import com.btg_pactual.domain.enums.TransactionType;
import com.btg_pactual.domain.exceptions.SubscriptionNotFoundException;
import com.btg_pactual.domain.strategies.ValidationStrategy;
import com.btg_pactual.domain.value_objects.Amount;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {
    private int id;
    private String name;
    private String email;
    private String phone;
    private NotificationType notification;
    private Amount balance;
    private List<Subscription> subscriptions = new ArrayList<>();
    private List<ValidationStrategy> validations = new ArrayList<>();

    public Client(int id, String name, String email, String phone, NotificationType notification, Amount balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.notification = notification;
        this.balance = balance;
    }

    public Transaction subscribeToFund(Fund fund, Amount amount) {
        for (ValidationStrategy validation : validations) {
            validation.canSubscribe(this, fund, amount);
        }

        Subscription subscription = new Subscription(fund.getId(), fund.getName(), amount);
        this.subscriptions.add(subscription);
        this.balance = this.balance.subtract(amount);

        return new Transaction(this.id, fund, amount, TransactionType.OPENING);
    }

    public Transaction cancelFundSubscription(Fund fund) {
        Optional<Subscription> subscriptionOpt = getSubscription(fund);
        Subscription subscription = subscriptionOpt
            .orElseThrow(() -> new SubscriptionNotFoundException(fund.getName()));

        this.balance = this.balance.add(subscription.getAmount());
        this.subscriptions.remove(subscription);

        return new Transaction(this.id, fund, subscription.getAmount(), TransactionType.CANCELLATION);
    }

    public boolean isSubscribed(Fund fund) {
        return this.subscriptions.stream().anyMatch(sub -> sub.getFundId() == fund.getId());
    }

    public Optional<Subscription> getSubscription(Fund fund) {
        return this.subscriptions.stream().filter(sub -> sub.getFundId() == fund.getId()).findFirst();
    }
}
