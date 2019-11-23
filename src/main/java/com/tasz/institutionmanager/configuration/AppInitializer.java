package com.tasz.institutionmanager.configuration;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
class AppInitializer {

    private final UserAdminService userAdminService;
    private final String username;
    private final String password;

    @Autowired
    public AppInitializer(final UserAdminService userAdminService,
                          @Value("${admin.user}") final String username,
                          @Value("${admin.password}") final String password) {
        this.userAdminService = userAdminService;
        this.username = username;
        this.password = password;
    }

    @PostConstruct
    private void init() {
        log.info("Initializing admin user ...");
        final UserDetails user = userAdminService.getUser(username);
        if (null == user) {
            final UserDetails userRegistrationDetails = new UserDetails();
            userRegistrationDetails.setUsername(username);
            userRegistrationDetails.setRole(Role.ADMIN);
            userRegistrationDetails.setName("Admin");
            userAdminService.addUser(userRegistrationDetails);

            userAdminService.setPassword(username, password);
        }
    }
}