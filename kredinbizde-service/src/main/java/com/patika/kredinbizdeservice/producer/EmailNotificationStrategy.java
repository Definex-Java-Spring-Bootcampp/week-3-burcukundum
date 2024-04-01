package com.patika.kredinbizdeservice.producer;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;

public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public NotificationDTO createNotification(String message, String email) {
        // Create and return an Email NotificationDTO object
        return NotificationDTO.builder()
                .notificationType(NotificationType.EMAIL)
                .message(message)
                .email(email)
                .build();
    }
}