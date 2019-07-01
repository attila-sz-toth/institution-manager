package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.contract.User;
import com.tasz.institutionmanager.dao.RolesDao;
import com.tasz.institutionmanager.model.UsersDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserToUsersDtoConverter implements Converter<User, UsersDto> {

    private final RolesDao rolesDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UsersDto convert(User from) {
        final UsersDto usersDto = new UsersDto();
        usersDto.setUsername(from.getUsername());
        usersDto.setPassword(passwordEncoder.encode(from.getPassword()));
        usersDto.setRole(rolesDao.findByRole(Role.valueOf(from.getRole())));

        return usersDto;
    }
}
