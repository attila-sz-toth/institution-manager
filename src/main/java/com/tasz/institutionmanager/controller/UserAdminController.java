package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public void addUser(@RequestBody final User user) {
        log.info("Adding new user: {}", user.getUsername());
        userAdminService.addUser(user);
    }

    @PostMapping(value = "set-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void setPassword(@RequestBody final String password) {
        final String userName = "admin";
        log.info("Setting password for user: {}", userName);
        userAdminService.setPassword(userName, password);
    }
}
