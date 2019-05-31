package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.converter.UserToUsersDtoConverter;
import com.tasz.institutionmanager.converter.UsersDtoToUserConverter;
import com.tasz.institutionmanager.dao.UsersDao;
import com.tasz.institutionmanager.model.UsersDto;
import com.tasz.institutionmanager.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private UsersDao usersDao;
    private UserToUsersDtoConverter userToUsersDtoConverter;
    private UsersDtoToUserConverter usersDtoToUserConverter;

    @Override
    public void addUser(final User user) {
        final UsersDto usersDto = userToUsersDtoConverter.convert(user);
        usersDao.save(usersDto);
    }

    @Override
    public void setPassword(final User user, final String password) {
        final UsersDto usersDto = new UsersDto();
        usersDto.setPassword(password);

        usersDao.save(usersDto);
    }

    @Override
    public Optional<User> getUserByUserName(final String username) {
        final Optional<UsersDto> usersDto = usersDao.findById(username);
        return usersDto.map(dto -> usersDtoToUserConverter.convert(dto));
    }
}
