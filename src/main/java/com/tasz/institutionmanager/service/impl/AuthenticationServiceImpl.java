package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.LoginData;
import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.converter.UserToUsersDtoConverter;
import com.tasz.institutionmanager.converter.UsersDtoToUserConverter;
import com.tasz.institutionmanager.dao.UsersDao;
import com.tasz.institutionmanager.model.UsersDto;
import com.tasz.institutionmanager.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersDao usersDao;
    private final UserToUsersDtoConverter userToUsersDtoConverter;
    private final UsersDtoToUserConverter usersDtoToUserConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.salt}")
    private String salt;

    @Override
    public void addUser(final User user) {
        final UsersDto usersDto = userToUsersDtoConverter.convert(user);
        usersDao.save(usersDto);
    }

    @Override
    @Transactional
    public void setPassword(final LoginData loginData) {
        final String hashedPassword = hashPassword(loginData.getPassword());
        usersDao.setPassword(loginData.getUsername(), hashedPassword);
    }

    private String hashPassword(final String password) {
        final String saltedPassword = password.concat(salt);
        return passwordEncoder.encode(saltedPassword);
    }

    @Override
    public User login(final LoginData loginData) {
        final UsersDto usersDto = usersDao.findByUsername(loginData.getUsername());

        final String password = loginData.getPassword();
        if (!isPasswordValid(password, usersDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return usersDtoToUserConverter.convert(usersDto);
    }

    private boolean isPasswordValid(final String inputPassword, final String storedPassword) {
        if (inputPassword == null) {
            return false;
        }

        final String saltedInputPassword = inputPassword.concat(salt);
        final String hashedInputPassword = passwordEncoder.encode(saltedInputPassword);

        return hashedInputPassword.equals(storedPassword);
    }
}
