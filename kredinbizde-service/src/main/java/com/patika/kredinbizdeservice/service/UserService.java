package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.EmailNotificationStrategy;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.NotificationStrategy;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import com.patika.kredinbizdeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository = new UserRepository();

    private final NotificationProducer notificationProducer;
    private NotificationStrategy notificationStrategy;

    private ApplicationRepository applicationRepository ;


    public User save(User user) {
        System.out.println("userRepository: " + userRepository.hashCode());

        userRepository.save(user);

        String message = "User has been saved";
        NotificationStrategy emailStrategy = new EmailNotificationStrategy();
        NotificationDTO emailNotification = emailStrategy.createNotification(message, user.getEmail());

        throw new KredinbizdeException("Customer not found with id: ");

        //return user;
    }

    public List<User> getAll() {
        System.out.println("userRepository: " + userRepository.hashCode());
        return userRepository.getAll();
    }

    public User getByEmail(String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));

        if (foundUser.isPresent()) {
            user = foundUser.get();
        }

        //throw new RuntimeException();

        // throw new NullPointerException();

         //throw new IllegalArgumentException("exception fırlatıldı");

        // throw new ArithmeticException();

        return user;

    }

    public User update(String email, User user) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        foundUser.get().setName(user.getName());

        foundUser.get().setSurname(user.getSurname());

        userRepository.delete(user);

        userRepository.save(foundUser.get());

        return foundUser.get();
    }

    public User getById(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
