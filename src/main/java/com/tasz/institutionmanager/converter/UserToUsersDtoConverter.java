package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.dao.RolesDao;
import com.tasz.institutionmanager.model.UsersDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserToUsersDtoConverter implements Converter<User, UsersDto> {

    private RolesDao rolesDao;

    @Override
    public UsersDto convert(User from) {
        final UsersDto usersDto = new UsersDto();
        usersDto.setUsername(from.getUsername());
        usersDto.setRole(rolesDao.findByRole(from.getRole()));

        return usersDto;
    }
}
