package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter implements Converter<UserEntity, UserDetails> {
    @Override
    public UserDetails convert(UserEntity userEntity) {
        final UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(userEntity, userDetails);

        userDetails.setRole(Role.valueOf(userEntity.getRoleEntity().getRoleName()));
        if (userEntity.getInstitutionEntity() != null) {
            userDetails.setInstitution(userEntity.getInstitutionEntity().getName());
        }

        return userDetails;
    }
}