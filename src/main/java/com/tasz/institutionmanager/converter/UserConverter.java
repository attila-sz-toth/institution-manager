package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserRegistrationDetails;
import com.tasz.institutionmanager.model.UserEntity;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class UserConverter implements Converter<UserRegistrationDetails, UserEntity> {

    private final RoleRepository roleRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public UserEntity convert(UserRegistrationDetails from) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(from.getUsername());
        userEntity.setRoleEntity(roleRepository.findByRoleName(from.getRole()));
        userEntity.setInstitutionEntitySet(Set.of(institutionRepository.findByName(from.getInstitutionName())));

        return userEntity;
    }
}
