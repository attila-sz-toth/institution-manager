package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InstitutionDetailsConverter implements Converter<InstitutionEntity, InstitutionDetails> {
    @Override
    public InstitutionDetails convert(InstitutionEntity institutionEntity) {
        final InstitutionDetails institutionDetails = new InstitutionDetails();
        BeanUtils.copyProperties(institutionEntity, institutionDetails);

        institutionDetails.setCareTypes(institutionEntity.getCareTypes().stream()
                .map(institutionCareType -> CareType.valueOf(institutionCareType.getCareType()))
                .collect(Collectors.toSet()));

        return institutionDetails;
    }
}
