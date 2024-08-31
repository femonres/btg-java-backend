package com.btg_pactual.infrastructure.messaging;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.btg_pactual.domain.model.Client;
import com.btg_pactual.domain.model.Fund;
import com.btg_pactual.domain.services.NotificationService;

public class SNSNotificationServiceImpl implements NotificationService {
    private final AmazonSNS snsClient;
    
    @Value("${aws.sns.email-topic-arn}")
    private String emailTopicArn;
    
    @Value("${aws.sns.sms-topic-arn}")
    private String smsTopicArn;
    
    public SNSNotificationServiceImpl(AmazonSNS snsClient) {
        this.snsClient = snsClient;
    }

    @Override
    public void sendSubscriptionNotification(Client client, Fund fund) {
        String message = "You have successfully subscribed to the fund " + fund.getName() + "!";
        switch (client.getNotification()) {
            case EMAIL:
                sendEmail(client.getName(), client.getEmail(), message);
                break;
            case PHONE:
                sendSMS(client.getName(), client.getPhone(), message);
                break;
        }
    }
    @Override
    public void sendUnsubscriptionNotification(Client client, Fund fund) {
        String message = "You have successfully unsubscribed from the fund " + fund.getName() + "!";
        switch (client.getNotification()) {
            case EMAIL:
                sendEmail(client.getName(), client.getEmail(), message);
                break;
            case PHONE:
                sendSMS(client.getName(), client.getPhone(), message);
                break;
        }
    }

    private void sendEmail(String username, String email, String message) {
        var attributes = Map.of(
            "email", new MessageAttributeValue().withDataType("String").withStringValue(email)
        );
        this.publishToSNS(emailTopicArn, attributes, username, message);
    }

    private void sendSMS(String username, String phone, String message) {
        var attributes = Map.of(
            "AWS.SNS.SMS.SMSType", new MessageAttributeValue().withDataType("String").withStringValue("Transactional"),
            "phone", new MessageAttributeValue().withDataType("String").withStringValue(phone)
        );
        this.publishToSNS(smsTopicArn, attributes, username, message);
    }

    private void publishToSNS(String topicArn, Map<String, MessageAttributeValue> attributes, String username, String message) {
        try {
            snsClient.publish(new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage(message)
                .withSubject("Notification for " + username)
                .withMessageAttributes(attributes));
        } catch (Exception e) {
            e.printStackTrace(); // Ideally, you should log this using a logging framework
        }
    }
}
