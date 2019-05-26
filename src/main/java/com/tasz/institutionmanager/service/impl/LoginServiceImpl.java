package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.model.User;
import com.tasz.institutionmanager.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public User getUserByUserName(String username) {
        return new User(username, Role.ADMIN);
    }
}
