package com.patika.kredinbizdeservice.producer;


import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;

public interface NotificationStrategy {

    NotificationDTO createNotification(String message, String email);
}
