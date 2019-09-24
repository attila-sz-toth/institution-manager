package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.Institution;
import com.tasz.institutionmanager.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User from) {
        return UserDetails.builder()
                .username(from.getUsername())
                .role(from.getRole().getRoleName())
                .institutions(from.getInstitutionSet().stream()
                        .map(Institution::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
