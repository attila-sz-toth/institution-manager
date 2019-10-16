package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.converter.UserConverter;
import com.tasz.institutionmanager.converter.UserDetailsConverter;
import com.tasz.institutionmanager.model.UserEntity;
import com.tasz.institutionmanager.repository.UserRepository;
import com.tasz.institutionmanager.service.MessagingService;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private static final int DEFAULT_PASSWORD_LENGTH = 16;
    private static final int PAGE_SIZE = 5;

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserDetailsConverter userDetailsConverter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessagingService messagingService;

    @Override
    @Transactional
    public Page<UserDetails> getUsers(final Integer pageNumber) {
        final Page<UserEntity> userEntities = userRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
        return userEntities
                .map(userDetailsConverter::convert);
    }

    @Override
    public UserDetails getUser(String username) {
        final UserEntity userEntity = userRepository.findByUsername(username);
        return userDetailsConverter.convert(userEntity);
    }

    @Override
    @Transactional
    public void addUser(final UserDetails userRegistrationDetails) {
        final String password = generatePassword();
        log.info("Password generated for user {} : {}", userRegistrationDetails.getUsername(), password);
        messagingService.sendPassword(userRegistrationDetails.getUsername(), password);

        final UserEntity userEntity = userConverter.convert(userRegistrationDetails);
        final String encodedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(encodedPassword);

        log.info("Adding new user {} with roleEntity {}", userRegistrationDetails.getUsername(), userRegistrationDetails.getRole());
        this.userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(final String username) {
        log.info("Deleting user {}", username);
        userRepository.deleteUserByUsername(username);
    }

    @Override
    @Transactional
    public void setPassword(final String userName, final String password) {
        final String encodedPassword = passwordEncoder.encode(password);
        userRepository.setPassword(userName, encodedPassword);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAscii(DEFAULT_PASSWORD_LENGTH);
    }
}
