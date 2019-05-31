package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.model.UsersDto;
import org.springframework.stereotype.Component;

@Component
public class UsersDtoToUserConverter implements Converter<UsersDto, User> {
    @Override
    public User convert(UsersDto from) {
        return User.builder()
                .username(from.getUsername())
                .role(from.getRole())
                .build();
    }
}
