package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InstitutionDetailsConverter implements Converter<InstitutionEntity, InstitutionDetails> {
    @Override
    public InstitutionDetails convert(InstitutionEntity from) {
        final InstitutionDetails institutionDetails = new InstitutionDetails();
        institutionDetails.setName(from.getName());
        institutionDetails.setAddress(from.getAddress());
        institutionDetails.setInstitutionType(from.getType());
        institutionDetails.setCareTypes(from.getCareTypes().stream()
                .map(institutionCareType -> CareType.valueOf(institutionCareType.getCareType()))
                .collect(Collectors.toSet()));

        return institutionDetails;
    }
}
