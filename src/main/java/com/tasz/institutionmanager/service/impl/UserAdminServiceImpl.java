package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.converter.UserConverter;
import com.tasz.institutionmanager.converter.UserDetailsConverter;
import com.tasz.institutionmanager.model.UserEntity;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.UserRepository;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private static final int DEFAULT_PASSWORD_LENGTH = 16;

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final UserConverter userConverter;
    private final UserDetailsConverter userDetailsConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<UserDetails> getUsers() {
        final Iterable<UserEntity> allUsers = userRepository.findAll();

        List<UserEntity> usersList = new ArrayList<>();
        allUsers.forEach(usersList::add);

        log.info("UserEntity List received: {}", usersList);
        return userDetailsConverter.convertAll(usersList);
    }

    @Override
    public UserDetails getUser(String username) {
        final UserEntity userEntity = userRepository.findByUsername(username);
        return userDetailsConverter.convert(userEntity);
    }

    @Override
    @Transactional
    public void addUser(final UserRegistrationDetails userRegistrationDetails) {
        final String password = generatePassword();
        log.info("Password generated for user {} : {}", userRegistrationDetails.getUsername(), password);

        final UserEntity usersDto = userConverter.convert(userRegistrationDetails);
        final String encodedPassword = passwordEncoder.encode(password);
        usersDto.setPassword(encodedPassword);

        log.info("Adding new user {} with roleEntity {}", userRegistrationDetails.getUsername(), userRegistrationDetails.getRole());
        this.userRepository.save(usersDto);
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

    @Override
    @Transactional
    public void updateInstitutionList(String userName, List<String> institutionList) {
        final UserEntity userEntity = userRepository.findByUsername(userName);
        userEntity.setInstitutionEntitySet(institutionList.stream()
                .map(institutionRepository::findByName)
                .collect(Collectors.toSet()));
    }

    private String generatePassword() {
        return RandomStringUtils.randomAscii(DEFAULT_PASSWORD_LENGTH);
    }
}
