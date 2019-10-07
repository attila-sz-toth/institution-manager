package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface CareReceiverRepository extends PagingAndSortingRepository<CareReceiverEntity, Integer> {
    Page<CareReceiverEntity> findAllByInstitutionEntity(final InstitutionEntity institutionEntity, final Pageable pageable);

    Page<CareReceiverEntity> findAllByInstitutionEntityAndCareStatus(final InstitutionEntity institutionEntity,
                                                                     final Pageable pageable, final CareStatus careStatus);

    Page<CareReceiverEntity> findAllByFirstName(final String firstName, final Pageable pageable);

    Page<CareReceiverEntity> findAllByLastName(final String lastName, final Pageable pageable);

    CareReceiverEntity findByFirstNameAndLastNameAndMothersNameAndBirthDate(final String firstName, final String LastName,
                                                                            final String mothersName, final Date birthDate);

    void deleteByFirstNameAndLastNameAndMothersNameAndBirthDate(final String firstName, final String LastName,
                                                                final String mothersName, final Date birthDate);
}
