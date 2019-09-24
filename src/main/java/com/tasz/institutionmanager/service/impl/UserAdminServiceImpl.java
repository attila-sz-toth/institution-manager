package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.converter.UserRegistrationDetailsToUserConverter;
import com.tasz.institutionmanager.converter.UserToUserDetailsConverter;
import com.tasz.institutionmanager.model.User;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private static final int DEFAULT_PASSWORD_LENGTH = 16;

    private final UserRepository userRepository;
    private final UserRegistrationDetailsToUserConverter userRegistrationDetailsToUserConverter;
    private final UserToUserDetailsConverter userToUserDetailsConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<UserDetails> getUsers() {
        final Iterable<User> allUsers = userRepository.findAll();

        List<User> usersList = new ArrayList<>();
        allUsers.forEach(usersList::add);

        log.info("User List received: {}", usersList);
        return userToUserDetailsConverter.convertAll(usersList);
    }

    @Override
    public UserDetails getUser(String username) {
        final User user = userRepository.findByUsername(username);
//        log.info("Retrieved user data: {}", user.;

        return userToUserDetailsConverter.convert(user);
    }

    @Override
    @Transactional
    public void addUser(final UserRegistrationDetails userRegistrationDetails) {
        final String password = generatePassword();
        log.info("Password generated for user {} : {}", userRegistrationDetails.getUsername(), password);

        final User usersDto = userRegistrationDetailsToUserConverter.convert(userRegistrationDetails);
        final String encodedPassword = passwordEncoder.encode(password);
        usersDto.setPassword(encodedPassword);

        log.info("Adding new user {} with role {}", userRegistrationDetails.getUsername(), userRegistrationDetails.getRole());
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

    private String generatePassword() {
        return RandomStringUtils.randomAscii(DEFAULT_PASSWORD_LENGTH);
    }
}
