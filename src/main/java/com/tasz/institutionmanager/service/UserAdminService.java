package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.contract.UserRegistrationDetails;

import java.util.List;


public interface UserAdminService {
    List<UserDetails> getUsers();

    UserDetails getUser(final String username);

    void addUser(final UserRegistrationDetails userRegistrationDetails);

    void deleteUser(final String username);

    void setPassword(final String userName, final String password);
}
