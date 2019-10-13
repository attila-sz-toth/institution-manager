package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.UserEntity;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter implements Converter<UserDetails, UserEntity> {

    private final RoleRepository roleRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public UserEntity convert(UserDetails userDetails) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDetails, userEntity);

        userEntity.setRoleEntity(roleRepository.findByRoleName(userDetails.getRole().name()));
        userEntity.setInstitutionEntity(institutionRepository.findByName(userDetails.getInstitution()));

        return userEntity;
    }
}
