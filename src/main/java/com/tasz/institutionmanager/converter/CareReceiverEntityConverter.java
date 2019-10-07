package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CareReceiverEntityConverter implements Converter<CareReceiverDetails, CareReceiverEntity> {

    private InstitutionRepository institutionRepository;

    @Override
    public CareReceiverEntity convert(CareReceiverDetails careReceiverDetails) {
        final CareReceiverEntity careReceiverEntity = new CareReceiverEntity();
        BeanUtils.copyProperties(careReceiverDetails, careReceiverEntity);

        final InstitutionEntity institutionEntity =
                institutionRepository.findByName(careReceiverDetails.getInstitutionName());
        careReceiverEntity.setInstitutionEntity(institutionEntity);

        return careReceiverEntity;
    }
}
