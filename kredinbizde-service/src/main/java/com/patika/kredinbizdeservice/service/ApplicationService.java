package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.AkbankServiceClient;
import com.patika.kredinbizdeservice.client.GarantiServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.AkbankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.request.GarantiApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository = new ApplicationRepository();
    private final ApplicationConverter applicationConverter;
    private final UserService userService;
    private final AkbankServiceClient akbankServiceClient;
    private final GarantiServiceClient garantiServiceClient;

    private List<Application> allApplicationList = new ArrayList<>();

    public Application createApplication(ApplicationRequest request) {

        User user = userService.getByEmail(request.getEmail());
        log.info("user bulundu");

        Application application = applicationConverter.toApplication(request, user);

        Application savedApplication = applicationRepository.save(application);
        allApplicationList.add(savedApplication);

        switch(request.getBankName()){
            case "Akbank":
                ApplicationResponse akbankApplicationResponse = akbankServiceClient.createApplication(prepareAkbankApplicationRequest(user));
                break;
            case "Garanti":
                ApplicationResponse garantiApplicationResponse = garantiServiceClient.createApplication(prepareGarantiApplicationRequest(user));
                break;
        }

        return savedApplication;
    }

    private AkbankApplicationRequest prepareAkbankApplicationRequest(User user) {
        AkbankApplicationRequest applicationRequest = new AkbankApplicationRequest();

        applicationRequest.setUserId(1L);

        return applicationRequest;
    }
    private GarantiApplicationRequest prepareGarantiApplicationRequest(User user) {
        GarantiApplicationRequest applicationRequest = new GarantiApplicationRequest();

        applicationRequest.setUserId(1L);

        return applicationRequest;
    }

    public ArrayList<Application> appsByEmail(String email) {

        User user = userService.getByEmail(email);

        ArrayList<Application> userApplications = new ArrayList<>();

        for (Application application : allApplicationList) {
            User userInTheAppList = application.getUser();

            if (userInTheAppList != null && userInTheAppList.equals(user)) {
                userApplications.add(application);
            }
        }
        return userApplications;

    }

}