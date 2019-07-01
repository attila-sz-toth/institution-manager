package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;


public interface UserAdminService {
    void addUser(final UserRegistrationDetails userRegistrationDetails);

    void setPassword(final String userName, final String password);
}
