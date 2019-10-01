package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.PersonalDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.service.PersonalDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {
    @Override
    public List<PersonalDetails> getPersonalDetailsList() {
        return null;
    }

    @Override
    public List<PersonalDetails> getPersonalDetailsListByInstitution(Integer institutionId) {
        return null;
    }

    @Override
    public List<PersonalDetails> getPersonalDetailsListByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<PersonalDetails> getPersonalDetailsListByLastName(String lastName) {
        return null;
    }

    @Override
    public PersonalDetails getPersonalDetails(PersonalDetailsCompositeKey compositeKey) {
        return null;
    }

    @Override
    public void addPersonalDetails(PersonalDetails personalDetails) {

    }

    @Override
    public void updatePersonalDetails(PersonalDetails personalDetails) {

    }

    @Override
    public void deletePersonalDetails(PersonalDetailsCompositeKey compositeKey) {

    }
}
