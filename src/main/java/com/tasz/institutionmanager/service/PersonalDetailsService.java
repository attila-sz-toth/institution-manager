package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.contract.PersonalDetails;

import java.util.List;

public interface PersonalDetailsService {
    List<PersonalDetails> getPersonalDetailsList();

    List<PersonalDetails> getPersonalDetailsListByInstitution(final Integer institutionId);

    List<PersonalDetails> getPersonalDetailsListByFirstName(final String firstName);

    List<PersonalDetails> getPersonalDetailsListByLastName(final String lastName);

    PersonalDetails getPersonalDetails(final PersonalDetailsCompositeKey compositeKey);

    void addPersonalDetails(final PersonalDetails personalDetails);

    void updatePersonalDetails(final PersonalDetails personalDetails);

    void deletePersonalDetails(final PersonalDetailsCompositeKey compositeKey);
}
