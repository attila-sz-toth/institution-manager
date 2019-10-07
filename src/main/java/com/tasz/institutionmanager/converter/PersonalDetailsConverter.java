package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.PersonalDetails;
import com.tasz.institutionmanager.model.PersonalDetailsEntity;
import org.springframework.beans.BeanUtils;


public class PersonalDetailsConverter implements Converter<PersonalDetailsEntity, PersonalDetails> {

    @Override
    public PersonalDetails convert(final PersonalDetailsEntity personalDetailsEntity) {
        final PersonalDetails personalDetails = new PersonalDetails();
        BeanUtils.copyProperties(personalDetailsEntity, personalDetails);

        return personalDetails;
    }
}
