package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.User;

public class UserToUserDetailsConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User from) {
        return UserDetails.builder()
                .username(from.getUsername())
                .role(from.getRole().getRoleName())
                .build();
    }
}
