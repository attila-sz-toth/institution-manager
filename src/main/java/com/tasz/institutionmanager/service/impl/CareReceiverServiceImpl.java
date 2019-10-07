package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.converter.CareReceiverConverter;
import com.tasz.institutionmanager.converter.CareReceiverEntityConverter;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.repository.CareReceiverRepository;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.service.CareReceiverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class CareReceiverServiceImpl implements CareReceiverService {

    private static final int PAGE_SIZE = 5;
    private static final Sort DEFAULT_SORTING =
            new Sort(Sort.Direction.ASC, "lastName", "firstName");

    private final InstitutionRepository institutionRepository;
    private final CareReceiverRepository careReceiverRepository;
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
        return careReceiverRepository.findAllByInstitutionEntity(institutionEntity, PageRequest.of(pageNumber, PAGE_SIZE, DEFAULT_SORTING))
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
    public void updateCareReceiver(CareReceiverDetails careReceiverDetails) {
        careReceiverRepository.save(careReceiverEntityConverter.convert(careReceiverDetails));
    }

    @Override
    @Transactional
    public void deleteCareReceiver(PersonalDetailsCompositeKey compositeKey) {
        careReceiverRepository.deleteByFirstNameAndLastNameAndMothersNameAndBirthDate(compositeKey.getFirstName(),
                compositeKey.getLastName(), compositeKey.getMothersName(), compositeKey.getBirthDate());
    }
}
