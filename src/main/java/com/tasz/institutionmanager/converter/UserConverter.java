package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.model.UserEntity;
import com.tasz.institutionmanager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter implements Converter<UserRegistrationDetails, UserEntity> {

    private final RoleRepository roleRepository;

    @Override
    public UserEntity convert(UserRegistrationDetails from) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(from.getUsername());
        userEntity.setRoleEntity(roleRepository.findByRoleName(from.getRole()));

        return userEntity;
    }
}
