package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserAdminService {
    Page<UserDetails> getUsers(Integer pageNumber);

    UserDetails getUser(final String username);

    void addUser(final UserRegistrationDetails userRegistrationDetails);

    void deleteUser(final String username);

    void setPassword(final String userName, final String password);

    void updateInstitutionList(final String userName, final List<String> institutionList);
}
