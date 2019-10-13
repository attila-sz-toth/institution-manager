package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.UserDetails;
import org.springframework.data.domain.Page;


public interface UserAdminService {
    Page<UserDetails> getUsers(Integer pageNumber);

    UserDetails getUser(final String username);

    void addUser(final UserDetails userRegistrationDetails);

    void deleteUser(final String username);

    void setPassword(final String userName, final String password);
}
