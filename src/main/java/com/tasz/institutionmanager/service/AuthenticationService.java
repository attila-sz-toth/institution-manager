package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.LoginData;
import com.tasz.institutionmanager.contract.User;


public interface AuthenticationService {
    void addUser(final User user);

    void setPassword(final LoginData loginData);

    User login(final LoginData username);
}
