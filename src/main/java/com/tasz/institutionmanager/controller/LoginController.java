package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
public class LoginController {

    private UserAdminService userAdminService;

    @GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> login(Authentication authentication) throws IllegalAccessException {
        final String userName = authentication.getName();
        final String role = authentication.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalAccessException("No role found"))
                .getAuthority();
        log.info("Logging in user: {}", userName);

        if (Role.valueOf(role) == Role.EMPLOYEE) {
            final UserDetails user = userAdminService.getUser(userName);
            return Map.of(
                    "username", userName,
                    "role", role,
                    "institution", user.getInstitution()
            );

        } else {
            return Map.of(
                    "username", userName,
                    "role", role
            );
        }
    }
}
