package com.patika.kredinbizdeservice.converter;

import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.HouseLoan;
import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationConverter {

    public Application toApplication(ApplicationRequest request, User user) {
        Application application = new Application();
        HouseLoan houseLoan= new HouseLoan(request.getAmount());

        application.setUser(user);
        application.setLoan(houseLoan);
        application.setLocalDateTime(LocalDateTime.now());
        application.setBankName(request.getBankName());

        return application;
    }
}
