package com.tasz.institutionmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> login(Authentication authentication) {
        final String userName = authentication.getName();
        final String role = authentication.getAuthorities().toString();
        log.info("Logging in user: {}", userName);

        return Map.of(
                "username", userName,
                "role", role
        );
    }
}
