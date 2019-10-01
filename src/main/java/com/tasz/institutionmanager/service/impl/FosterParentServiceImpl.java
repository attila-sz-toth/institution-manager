package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.FosterParentDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.service.FosterParentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FosterParentServiceImpl implements FosterParentsService {
    @Override
    public FosterParentDetails getFosterParentDetails(PersonalDetailsCompositeKey compositeKey) {
        return null;
    }

    @Override
    public List<FosterParentDetails> getFosterParentsDetailsList(PersonalDetailsCompositeKey childCompositeKey) {
        return null;
    }

    @Override
    public void addFosterParentDetails(FosterParentDetails fosterParentDetailsDetails) {

    }

    @Override
    public void updateFosterParentDetails(FosterParentDetails fosterParentDetails) {

    }

    @Override
    public void removeFosterParentDetails(PersonalDetailsCompositeKey compositeKey) {

    }
}
