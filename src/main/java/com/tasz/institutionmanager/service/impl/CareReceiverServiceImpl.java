package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.converter.CareReceiverConverter;
import com.tasz.institutionmanager.converter.CareReceiverEntityConverter;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.model.NormativeEntity;
import com.tasz.institutionmanager.repository.CareReceiverRepository;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.NormativeRepository;
import com.tasz.institutionmanager.serializer.DateSerializer;
import com.tasz.institutionmanager.service.CareReceiverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
@AllArgsConstructor
public class CareReceiverServiceImpl implements CareReceiverService {

    static final int PAGE_SIZE = 5;
    static final Sort DEFAULT_SORTING =
            new Sort(Sort.Direction.ASC, "lastName", "firstName");

    private final InstitutionRepository institutionRepository;
    private final CareReceiverRepository careReceiverRepository;
    private final NormativeRepository normativeRepository;
    private final CareReceiverConverter careReceiverConverter;
    private final CareReceiverEntityConverter careReceiverEntityConverter;

    @Override
    public Page<CareReceiverDetails> getCareReceivers(final Integer pageNumber) {
        return careReceiverRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING))
                .map(careReceiverConverter::convert);
    }

    @Override
    public Page<CareReceiverDetails> getCareReceiversByInstitution(final Integer pageNumber, final String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.findAllByInstitutionEntityAndCareStatus(institutionEntity,
                PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING), CareStatus.ACTIVE)
                .map(careReceiverConverter::convert);
    }

    @Override
    public Page<CareReceiverDetails> getWaitingListByInstitution(Integer pageNumber, String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.findAllByInstitutionEntityAndCareStatus(institutionEntity,
                PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING), CareStatus.WAITING)
                .map(careReceiverConverter::convert);
    }

    @Override
    public Page<CareReceiverDetails> getSuspendedListByInstitution(Integer pageNumber, String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.findAllByInstitutionEntityAndCareStatus(institutionEntity,
                PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING), CareStatus.SUSPENDED)
                .map(careReceiverConverter::convert);
    }

    @Override
    public Page<CareReceiverDetails> getTerminatedListByInstitution(Integer pageNumber, String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.findAllByInstitutionEntityAndCareStatus(institutionEntity,
                PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING), CareStatus.TERMINATED)
                .map(careReceiverConverter::convert);
    }

    @Override
    public Page<CareReceiverDetails> getCareReceiversByFirstName(final Integer pageNumber, final String firstName) {
        return careReceiverRepository.findAllByFirstName(firstName, PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING))
                .map(careReceiverConverter::convert);
    }

    @Override
    public Page<CareReceiverDetails> getCareReceiversByLastName(final Integer pageNumber, final String lastName) {
        return careReceiverRepository.findAllByLastName(lastName, PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING))
                .map(careReceiverConverter::convert);
    }

    @Override
    public CareReceiverDetails getCareReceiver(PersonalDetailsCompositeKey compositeKey) {
        final CareReceiverEntity careReceiverEntity =
                careReceiverRepository.findByFirstNameAndLastNameAndMothersNameAndBirthDate(compositeKey.getFirstName(),
                        compositeKey.getLastName(), compositeKey.getMothersName(), compositeKey.getBirthDate());

        return careReceiverConverter.convert(careReceiverEntity);
    }

    @Override
    @Transactional
    public void addCareReceiver(final CareReceiverDetails careReceiverDetails) {
        careReceiverRepository.save(careReceiverEntityConverter.convert(careReceiverDetails));
    }

    @Override
    @Transactional
    public void updateCareReceiver(final PersonalDetailsCompositeKey key, final CareReceiverDetails careReceiverDetails) {
        final CareReceiverEntity careReceiverEntity = careReceiverRepository.findByFirstNameAndLastNameAndMothersNameAndBirthDate(
                key.getFirstName(),
                key.getLastName(),
                key.getMothersName(),
                key.getBirthDate()
        );

        BeanUtils.copyProperties(careReceiverDetails, careReceiverEntity);
        final InstitutionEntity institutionEntity =
                institutionRepository.findByName(careReceiverDetails.getInstitutionName());
        careReceiverEntity.setInstitutionEntity(institutionEntity);

        careReceiverRepository.save(careReceiverEntity);
    }

    @Override
    @Transactional
    public void deleteCareReceiver(final PersonalDetailsCompositeKey compositeKey) {
        careReceiverRepository.deleteByFirstNameAndLastNameAndMothersNameAndBirthDate(compositeKey.getFirstName(),
                compositeKey.getLastName(), compositeKey.getMothersName(), compositeKey.getBirthDate());
    }

    @Override
    public Integer countCareReceiversByInstitution(final String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.countByInstitutionEntityAndCareStatus(institutionEntity, CareStatus.ACTIVE);
    }

    @Override
    public Integer countWaitingListByInstitution(final String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.countByInstitutionEntityAndCareStatus(institutionEntity, CareStatus.WAITING);
    }

    @Override
    public Integer countArchiveByInstitution(final String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return careReceiverRepository.countByInstitutionEntityAndCareStatus(institutionEntity, CareStatus.TERMINATED);
    }

    @Override
    public Integer countCareReceiversByInstitutionInTimeRange(final String institutionName, final String fromDateString,
                                                              final String toDateString) throws ParseException {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final Date fromDate = DateSerializer.dateFormat.parse(fromDateString);
        final Date toDate = DateSerializer.dateFormat.parse(toDateString);

        return careReceiverRepository.countCareReceiversByInstitutionInTimeRange(institutionEntity, fromDate, toDate);
    }

    @Override
    public Integer normativeByInstitutionAndYear(final String institutionName, final String year) throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date fromDate = dateFormat.parse(year + "-01-01");
        final Date toDate = dateFormat.parse(year + "-12-31");

        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final NormativeEntity normativeEntity = normativeRepository.findByInstitutionEntityAndYear(institutionEntity, year);
        final Integer normative = null != normativeEntity ? normativeEntity.getAmount() : 0;

        final Set<CareReceiverEntity> receivers
                = careReceiverRepository.findCareReceiversByInstitutionInTimeRange(institutionEntity, fromDate, toDate);

        final Integer careDays = receivers.stream()
                .map(careReceiver -> DAYS.between(getStartInstant(careReceiver, fromDate), getEndInstant(careReceiver, toDate)))
                .mapToInt(Long::intValue)
                .sum();

        return normative * careDays;
    }

    private Instant getStartInstant(final CareReceiverEntity careReceiver, final Date fromDate) {
        final Date startOfCare = careReceiver.getStartOfCare();
        final Date startDate = null != startOfCare && fromDate.compareTo(startOfCare) <= 0 ?
                startOfCare :
                fromDate;
        return startDate.toInstant();
    }

    private Instant getEndInstant(final CareReceiverEntity careReceiver, final Date toDate) {
        final Date endOfCare = careReceiver.getEndOfCare();
        final Date endDate = null != endOfCare && toDate.compareTo(endOfCare) < 0 ?
                endOfCare :
                toDate;
        return endDate.toInstant();
    }
}
