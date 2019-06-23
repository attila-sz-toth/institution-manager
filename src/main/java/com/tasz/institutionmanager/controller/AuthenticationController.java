package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.LoginData;
import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @GetMapping(value = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User login(@RequestBody final LoginData loginData) {
        log.info("User is attempting to login: {}", loginData.getUsername());
        return authenticationService.login(loginData);
    }

    @PostMapping(value = "add-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addUser(@RequestBody final User user) {
        log.info("Adding new user: {}", user.getUsername());
        authenticationService.addUser(user);
    }

    @PostMapping(value = "set-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void setPassword(@RequestBody final LoginData loginData) {
        log.info("Setting password for user: {}", loginData.getUsername());
        authenticationService.setPassword(loginData);
    }
}
