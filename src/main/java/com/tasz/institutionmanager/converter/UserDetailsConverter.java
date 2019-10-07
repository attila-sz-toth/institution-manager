package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.UserDetails;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDetailsConverter implements Converter<UserEntity, UserDetails> {
    @Override
    public UserDetails convert(UserEntity userEntity) {
        final UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(userEntity, userDetails);

        userDetails.setInstitutions(userEntity.getInstitutionEntitySet().stream()
                .map(InstitutionEntity::getName)
                .collect(Collectors.toList()));

        return userDetails;
    }
}