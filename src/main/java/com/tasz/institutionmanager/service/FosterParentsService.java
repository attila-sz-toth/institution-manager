package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.FosterParentDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;

import java.util.List;

public interface FosterParentsService {
    FosterParentDetails getFosterParentDetails(final PersonalDetailsCompositeKey compositeKey);

    List<FosterParentDetails> getFosterParentsDetailsList(final PersonalDetailsCompositeKey childCompositeKey);

    void addFosterParentDetails(final FosterParentDetails fosterParentDetailsDetails);

    void updateFosterParentDetails(final FosterParentDetails fosterParentDetails);

    void removeFosterParentDetails(final PersonalDetailsCompositeKey compositeKey);
}
