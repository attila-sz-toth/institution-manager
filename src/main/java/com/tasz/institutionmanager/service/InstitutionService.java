package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.FosterParentDetails;
import com.tasz.institutionmanager.contract.InstitutionDetails;

import java.util.List;

public interface InstitutionService {
    List<InstitutionDetails> getInstitutions();

    InstitutionDetails getInstitutionDetails(final String institutionName);

    void addInstitution(final InstitutionDetails institutionDetails);

    void deleteInstitution(final String institutionName);

    void updateInstitution(final String institutionName, final InstitutionDetails institutionDetails);

    Integer getNumberOfCareReceivers();

    Integer getSizeOfWaitingList();

    List<FosterParentDetails> getFosterParents(final String institutionName);
}
