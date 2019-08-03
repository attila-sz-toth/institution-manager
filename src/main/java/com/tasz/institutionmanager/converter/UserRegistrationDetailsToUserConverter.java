package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.model.User;
import com.tasz.institutionmanager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRegistrationDetailsToUserConverter implements Converter<UserRegistrationDetails, User> {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User convert(UserRegistrationDetails from) {
        final User user = new User();
        user.setUsername(from.getUsername());
        user.setRole(roleRepository.findByRoleName(from.getRole()));

        return user;
    }
}
