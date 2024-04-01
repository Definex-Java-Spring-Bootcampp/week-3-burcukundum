package com.patika.kredinbizdeservice.producer;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;

public class MobileNotificationStrategy implements NotificationStrategy {
    @Override
    public NotificationDTO createNotification(String message, String email) {
        // Create and return a Mobile NotificationDTO object
        return NotificationDTO.builder()
                .notificationType(NotificationType.MOBILE_NOTIFICATION)
                .message(message)
                .build();
    }
}
