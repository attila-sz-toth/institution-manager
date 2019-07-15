package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserAdminController {

    private UserAdminService userAdminService;

    @PostMapping(value = "add-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addUser(@RequestBody final UserRegistrationDetails userRegistrationDetails) {
        log.info("Adding new userRegistrationDetails: {}", userRegistrationDetails.getUsername());
        userAdminService.addUser(userRegistrationDetails);
    }

    @PostMapping(value = "set-password", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void setPassword(final Authentication authentication, @RequestBody final String password) {
        final String userName = authentication.getName();
        log.info("Setting password for user: {}", userName);
        userAdminService.setPassword(userName, password);
    }
}
