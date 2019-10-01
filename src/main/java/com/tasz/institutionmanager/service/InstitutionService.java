package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.contract.FosterParentDetails;
import com.tasz.institutionmanager.contract.InstitutionDetails;

import java.util.List;
import java.util.Set;

public interface InstitutionService {
    List<InstitutionDetails> getInstitutions();

    InstitutionDetails getInstitutionDetails(final String institutionName);

    void addInstitution(final InstitutionDetails institutionDetails);

    void deleteInstitution(final String institutionName);

    void updateInstitution(final String institutionName, final InstitutionDetails institutionDetails);

    void addCareTypes(final String institutionName, final Set<CareType> careTypes);

    void deleteCareTypes(final String institutionName, final Set<CareType> careTypes);

    Integer getNumberOfCareReceivers();

    Integer getSizeOfWaitingList();

    List<FosterParentDetails> getFosterParents(final String institutionName);
}
