package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.model.User;


public interface LoginService {
    User getUserByUserName(final String username);
}
