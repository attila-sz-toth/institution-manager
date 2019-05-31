package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.User;

import java.util.Optional;


public interface AuthenticationService {
    void addUser(final User user);

    void setPassword(final User user, final String password);

    Optional<User> getUserByUserName(final String username);
}
