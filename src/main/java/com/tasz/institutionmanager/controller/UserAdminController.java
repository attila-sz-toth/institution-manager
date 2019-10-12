package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAdminController {

    private UserAdminService userAdminService;

    @GetMapping(value = "get-users/{page-number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDetails> getUsers(@PathVariable(value = "page-number") final Integer pageNumber) {
        log.info("Getting users list");
        return userAdminService.getUsers(pageNumber);
    }

    @GetMapping(value = "get-user", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetails getUser(@RequestBody final String username) {
        log.info("Getting user data for user: {}", username);
        return userAdminService.getUser(username);
    }

    @PostMapping(value = "add-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addUser(@RequestBody final UserRegistrationDetails userRegistrationDetails) {
        log.info("Adding new userRegistrationDetails: {}", userRegistrationDetails.getUsername());
        userAdminService.addUser(userRegistrationDetails);
    }

    @PostMapping(value = "delete-user", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestBody final String username) {
        log.info("Deleting user: {}", username);
        userAdminService.deleteUser(username);
    }

    @PostMapping(value = "set-password", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void setPassword(final Authentication authentication, @RequestBody final String password) {
        final String userName = authentication.getName();
        log.info("Setting password for user: {}", userName);
        userAdminService.setPassword(userName, password);
    }

    @GetMapping(value = "get-roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getRoles() {
        return List.of(Role.values());
    }
}
