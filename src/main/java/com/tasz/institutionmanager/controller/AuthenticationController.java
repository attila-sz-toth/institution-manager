package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.LoginData;
import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User login(@RequestBody final LoginData loginData) {
        log.info("User is attempting to login: {}", loginData.getUsername());

        final Optional<User> user = authenticationService.getUserByUserName(loginData.getUsername());
        if (user.isPresent()) {
            return user.get();
        } else {
            log.error("No user found: {}", loginData.getUsername());
            throw new IllegalArgumentException("No user found!");
        }
    }

    @PostMapping(value = "/add-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody final User user) {
        log.info("Adding new user: {}", user.getUsername());
        authenticationService.addUser(user);
    }
}
