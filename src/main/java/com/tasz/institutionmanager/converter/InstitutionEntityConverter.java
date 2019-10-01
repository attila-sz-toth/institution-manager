package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.model.InstitutionCareType;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InstitutionEntityConverter implements Converter<InstitutionDetails, InstitutionEntity> {
    @Override
    public InstitutionEntity convert(final InstitutionDetails from) {
        return convert(from, new InstitutionEntity());
    }

    public InstitutionEntity convert(final InstitutionDetails from, final InstitutionEntity to) {
        to.setName(from.getName());
        to.setAddress(from.getAddress());
        to.setType(from.getInstitutionType());
        to.setCareTypes(from.getCareTypes().stream()
                .map(careType -> {
                    final InstitutionCareType institutionCareType = new InstitutionCareType();
                    institutionCareType.setCareType(careType.name());
                    institutionCareType.setInstitutionEntity(to);

                    return institutionCareType;
                }).collect(Collectors.toSet()));

        return to;
    }
}
