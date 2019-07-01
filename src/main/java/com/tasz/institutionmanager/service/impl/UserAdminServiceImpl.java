package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.converter.UserToUsersDtoConverter;
import com.tasz.institutionmanager.dao.UsersDao;
import com.tasz.institutionmanager.model.UsersDto;
import com.tasz.institutionmanager.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UsersDao usersDao;
    private final UserToUsersDtoConverter userToUsersDtoConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void addUser(final User user) {
        final UsersDto usersDto = userToUsersDtoConverter.convert(user);
        usersDao.save(usersDto);
    }

    @Override
    @Transactional
    public void setPassword(final String userName, final String password) {
        final String encodedPassword = passwordEncoder.encode(password);
        usersDao.setPassword(userName, encodedPassword);
    }

}
