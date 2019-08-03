package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.converter.UserRegistrationDetailsToUserConverter;
import com.tasz.institutionmanager.model.User;
import com.tasz.institutionmanager.repository.UserRepository;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;
    private final UserRegistrationDetailsToUserConverter userRegistrationDetailsToUserConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addUser(final UserRegistrationDetails userRegistrationDetails) {
        final String password = generateRandomSpecialCharacters(16);
        log.info("Password generated for user {} : {}", password);

        final User usersDto = userRegistrationDetailsToUserConverter.convert(userRegistrationDetails);
        usersDto.setPassword(password);

        this.userRepository.save(usersDto);
    }

    @Override
    @Transactional
    public void setPassword(final String userName, final String password) {
        final String encodedPassword = passwordEncoder.encode(password);
        userRepository.setPassword(userName, encodedPassword);
    }

    public String generateRandomSpecialCharacters(int length) {
        return RandomStringUtils.random(16);
    }

}
