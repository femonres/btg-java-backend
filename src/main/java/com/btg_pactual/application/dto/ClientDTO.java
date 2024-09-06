package com.btg_pactual.application.dto;

import java.util.List;

import com.btg_pactual.domain.enums.NotificationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private NotificationType notification;
    private int balance;
    private List<SubscriptionDTO> subscriptions;
}
