package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface CareReceiverService {
    Page<CareReceiverDetails> getCareReceivers(final Integer pageNumber);

    Page<CareReceiverDetails> getCareReceiversByInstitution(final Integer pageNumber, final String institutionName);

    Page<CareReceiverDetails> getWaitingListByInstitution(final Integer pageNumber, final String institutionName);

    Page<CareReceiverDetails> getSuspendedListByInstitution(final Integer pageNumber, final String institutionName);

    Page<CareReceiverDetails> getTerminatedListByInstitution(final Integer pageNumber, final String institutionName);

    Page<CareReceiverDetails> getCareReceiversByFirstName(final Integer pageNumber, final String firstName);

    Page<CareReceiverDetails> getCareReceiversByLastName(final Integer pageNumber, final String lastName);

    CareReceiverDetails getCareReceiver(final PersonalDetailsCompositeKey compositeKey);

    void addCareReceiver(final CareReceiverDetails careReceiverDetails);

    void updateCareReceiver(final PersonalDetailsCompositeKey key, final CareReceiverDetails careReceiverDetails);

    void deleteCareReceiver(final PersonalDetailsCompositeKey compositeKey);

    Integer countCareReceiversByInstitution(final String institutionName);

    Integer countWaitingListByInstitution(final String institutionName);

    Integer countArchiveByInstitution(final String institutionName);

    Integer countCareReceiversByInstitutionInTimeRange(String institutionName, String fromDate, String toDate) throws ParseException;

    Integer normativeByInstitutionAndYear(final String institutionName, final String date) throws ParseException;
}
