package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.User;


public interface UserAdminService {
    void addUser(final User user);

    void setPassword(final String userName, final String password);
}
