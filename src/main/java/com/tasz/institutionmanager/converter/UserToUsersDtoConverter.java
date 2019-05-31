package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.model.UsersDto;
import org.springframework.stereotype.Component;

@Component
public class UserToUsersDtoConverter implements Converter<User, UsersDto> {
    @Override
    public UsersDto convert(User from) {
        final UsersDto usersDto = new UsersDto();
        usersDto.setUsername(from.getUsername());
        usersDto.setRole(from.getRole());

        return usersDto;
    }
}
