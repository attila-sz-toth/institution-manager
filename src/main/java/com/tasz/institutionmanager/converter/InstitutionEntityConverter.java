package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.model.InstitutionCareTypeEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InstitutionEntityConverter implements Converter<InstitutionDetails, InstitutionEntity> {
    @Override
    public InstitutionEntity convert(final InstitutionDetails institutionDetails) {
        return convert(institutionDetails, new InstitutionEntity());
    }

    public InstitutionEntity convert(final InstitutionDetails institutionDetails, final InstitutionEntity institutionEntity) {
        BeanUtils.copyProperties(institutionDetails, institutionEntity);

        institutionEntity.setCareTypes(institutionDetails.getCareTypes().stream()
                .map(careType -> {
                    final InstitutionCareTypeEntity institutionCareTypeEntity = new InstitutionCareTypeEntity();
                    institutionCareTypeEntity.setCareType(careType.name());
                    institutionCareTypeEntity.setInstitutionEntity(institutionEntity);

                    return institutionCareTypeEntity;
                }).collect(Collectors.toSet()));

        return institutionEntity;
    }
}
