package com.patika.kredinbizdeservice.producer;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;

// SMSNotificationStrategy.java
public class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public NotificationDTO createNotification(String message, String email) {
        // Create and return an SMS NotificationDTO object
        return NotificationDTO.builder()
                .notificationType(NotificationType.SMS)
                .message(message)
                .build();
    }
}
