package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.converter.UserRegistrationDetailsToUserConverter;
import com.tasz.institutionmanager.model.User;
import com.tasz.institutionmanager.repository.UserRepository;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;
    private final UserRegistrationDetailsToUserConverter userRegistrationDetailsToUserConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addUser(final UserRegistrationDetails userRegistrationDetails) {
        final User usersDto = userRegistrationDetailsToUserConverter.convert(userRegistrationDetails);
        this.userRepository.save(usersDto);
    }

    @Override
    @Transactional
    public void setPassword(final String userName, final String password) {
        final String encodedPassword = passwordEncoder.encode(password);
        userRepository.setPassword(userName, encodedPassword);
    }

}
