package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Set;

public interface CareReceiverRepository extends PagingAndSortingRepository<CareReceiverEntity, Integer> {

    Page<CareReceiverEntity> findAllByInstitutionEntityAndCareStatus(final InstitutionEntity institutionEntity,
                                                                     final Pageable pageable, final CareStatus careStatus);

    Page<CareReceiverEntity> findAllByFirstName(final String firstName, final Pageable pageable);

    Page<CareReceiverEntity> findAllByLastName(final String lastName, final Pageable pageable);

    CareReceiverEntity findByFirstNameAndLastNameAndMothersNameAndBirthDate(final String firstName, final String LastName,
                                                                            final String mothersName, final Date birthDate);

    void deleteByFirstNameAndLastNameAndMothersNameAndBirthDate(final String firstName, final String LastName,
                                                                final String mothersName, final Date birthDate);

    Integer countByInstitutionEntityAndCareStatus(final InstitutionEntity institutionEntity, final CareStatus status);

    @Query(value = "select count(c) from CareReceiverEntity c where c.institutionEntity = :institution " +
            "and c.startOfCare <= :toDate and (c.endOfCare = null or c.endOfCare >= :fromDate)")
    Integer countCareReceiversByInstitutionInTimeRange(@Param("institution") InstitutionEntity institution,
                                                       @Param("fromDate") Date fromDate,
                                                       @Param("toDate") Date toDate);

    @Query(value = "select c from CareReceiverEntity c where c.institutionEntity = :institution " +
            "and c.startOfCare <= :toDate and (c.endOfCare = null or c.endOfCare >= :fromDate)")
    Set<CareReceiverEntity> findCareReceiversByInstitutionInTimeRange(@Param("institution") InstitutionEntity institution,
                                                                      @Param("fromDate") Date fromDate,
                                                                      @Param("toDate") Date toDate);
}
