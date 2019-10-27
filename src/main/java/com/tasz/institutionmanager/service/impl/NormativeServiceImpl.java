package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.contract.Normative;
import com.tasz.institutionmanager.converter.NormativeConverter;
import com.tasz.institutionmanager.converter.NormativeEntityConverter;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.model.NormativeEntity;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.NormativeRepository;
import com.tasz.institutionmanager.service.NormativeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NormativeServiceImpl implements NormativeService {

    private final NormativeRepository normativeRepository;
    private final InstitutionRepository institutionRepository;
    private final NormativeConverter normativeConverter;
    private final NormativeEntityConverter normativeEntityConverter;

    @Override
    public List<Normative> getNormativeByInstitution(final String institutionName) {
        log.info("Loading normative of institution {}", institutionName);

        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final List<NormativeEntity> normativeEntities = normativeRepository.findByInstitutionEntity(institutionEntity);

        return normativeConverter.convertAll(normativeEntities);
    }

    @Override
    public void addNewNormative(String institutionName, Normative normative) {
        log.info("Add new normative to institution {}", institutionName);

        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final NormativeEntity normativeEntity = normativeEntityConverter.convert(normative);
        normativeEntity.setInstitutionEntity(institutionEntity);

        normativeRepository.save(normativeEntity);
    }

    @Override
    public void updateNormativeAmount(String institutionName, String year, Integer amount) {
        log.info("Updating normative of {} for year {}", institutionName, year);
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final NormativeEntity normativeEntity = normativeRepository.findByInstitutionEntityAndYear(institutionEntity, year);
        normativeEntity.setAmount(amount);

        normativeRepository.save(normativeEntity);
    }

    @Override
    public void deleteNormative(String institutionName, String year) {
        log.info("Deleting normative of {} for year {}", institutionName, year);
        final InstitutionEntity institutionEntity = institutionRepository.findByName(institutionName);
        final NormativeEntity normativeEntity = normativeRepository.findByInstitutionEntityAndYear(institutionEntity, year);

        normativeRepository.delete(normativeEntity);
    }
}
