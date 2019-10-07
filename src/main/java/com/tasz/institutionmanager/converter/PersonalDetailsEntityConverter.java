package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.PersonalDetails;
import com.tasz.institutionmanager.model.PersonalDetailsEntity;
import org.springframework.beans.BeanUtils;

public class PersonalDetailsEntityConverter implements Converter<PersonalDetails, PersonalDetailsEntity> {

    @Override
    public PersonalDetailsEntity convert(PersonalDetails personalDetails) {
        final PersonalDetailsEntity personalDetailsEntity = new PersonalDetailsEntity();
        BeanUtils.copyProperties(personalDetails, personalDetailsEntity);

        return personalDetailsEntity;
    }
}
