package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.converter.InstitutionDetailsConverter;
import com.tasz.institutionmanager.converter.InstitutionEntityConverter;
import com.tasz.institutionmanager.model.InstitutionCareTypeEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.repository.InstitutionCareTypeRepository;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.service.InstitutionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class InstitutionServiceImpl implements InstitutionService {
    private InstitutionRepository institutionRepository;
    private InstitutionCareTypeRepository institutionCareTypeRepository;
    private InstitutionDetailsConverter institutionDetailsConverter;
    private InstitutionEntityConverter institutionEntityConverter;

    @Override
    public List<InstitutionDetails> getInstitutions() {
        final List<InstitutionEntity> institutionEntities = institutionRepository.findAll();
        return institutionDetailsConverter.convertAll(institutionEntities);
    }

    @Override
    public InstitutionDetails getInstitutionDetails(final String institutionName) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        return institutionDetailsConverter.convert(institutionEntity);
    }

    @Override
    @Transactional
    public void addInstitution(final InstitutionDetails institutionDetails) {
        final InstitutionEntity institutionEntity = institutionEntityConverter.convert(institutionDetails);
        institutionRepository.save(institutionEntity);
    }

    @Override
    @Transactional
    public void deleteInstitution(final String institutionName) {
        institutionRepository.deleteByName(institutionName);
    }

    @Override
    @Transactional
    public void updateInstitution(final String institutionName, final InstitutionDetails institutionDetails) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        institutionEntityConverter.convert(institutionDetails, institutionEntity);

        institutionRepository.save(institutionEntity);
    }

    @Override
    public void addCareTypes(final String institutionName, final Set<CareType> careTypes) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final Set<InstitutionCareTypeEntity> institutionCareTypeEntities = institutionEntity.getCareTypes();
        careTypes.stream()
                .filter(careType -> institutionCareTypeEntities.stream()
                        .noneMatch(institutionCareTypeEntity -> institutionCareTypeEntity.getCareType().equals(careType.name())))
                .forEach(careType -> {
                    final InstitutionCareTypeEntity institutionCareTypeEntity = new InstitutionCareTypeEntity();
                    institutionCareTypeEntity.setInstitutionEntity(institutionEntity);
                    institutionCareTypeEntity.setCareType(careType.name());

                    log.info("Persisting InstitutionCareTypeEntity entity {}", institutionCareTypeEntity);
                    institutionCareTypeRepository.save(institutionCareTypeEntity);
                });
    }

    @Override
    @Transactional
    public void deleteCareTypes(final String institutionName, final Set<CareType> careTypes) {
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        careTypes.forEach(
                careType -> institutionCareTypeRepository
                        .deleteByCareTypeAndInstitutionEntity(careType.name(), institutionEntity));

    }
}
