package com.btg_pactual.infrastructure.messaging;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.btg_pactual.domain.enums.NotificationType;
import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.services.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class SNSNotificationServiceImpl implements NotificationService {
    private final SnsClient snsClient;
    
    @Value("${aws.sns.email-topic-arn}")
    private String emailTopicArn;
    
    @Value("${aws.sns.sms-topic-arn}")
    private String smsTopicArn;

    @Override
    public void sendSubscriptionNotification(Client client, Fund fund) {
        String message = "You have successfully subscribed to the fund " + fund.getName() + "!";
        if (client.getNotification() == NotificationType.EMAIL) {
            sendEmail(client.getName(), client.getEmail(), message);
        } else if (client.getNotification() == NotificationType.PHONE) {
            sendSMS(client.getPhone(), message);
        }
    }
    @Override
    public void sendUnsubscriptionNotification(Client client, Fund fund) {
        String message = "You have successfully unsubscribed from the fund " + fund.getName() + "!";
        if (client.getNotification() == NotificationType.EMAIL) {
            sendEmail(client.getName(), client.getEmail(), message);
        } else if (client.getNotification() == NotificationType.PHONE) {
            sendSMS(client.getPhone(), message);
        }
    }

    private void sendEmail(String username, String email, String message) {
        Map<String, MessageAttributeValue> attributes = Map.of(
            "contact", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(email)
                .build()
        );
        PublishRequest publishRequest = PublishRequest.builder()
            .topicArn(emailTopicArn)
            .message(message)
            .messageAttributes(attributes)
            .subject(String.format("Notification for %s", username))
            .build();
        PublishResponse publishResponse = snsClient.publish(publishRequest);
        log.info("MessageId: " + publishResponse.messageId());
    }

    private void sendSMS(String phone, String message) {
        PublishRequest publishRequest = PublishRequest.builder()
            .topicArn(smsTopicArn)
            .message(message)
            .phoneNumber(phone)
            .build();
        PublishResponse publishResponse = snsClient.publish(publishRequest);
        log.info("MessageId: " + publishResponse.messageId());
    }
}
