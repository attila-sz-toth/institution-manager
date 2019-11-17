package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CareReceiverConverter implements Converter<CareReceiverEntity, CareReceiverDetails> {

    @Override
    public CareReceiverDetails convert(CareReceiverEntity careReceiverEntity) {
        final CareReceiverDetails careReceiverDetails = new CareReceiverDetails();
        BeanUtils.copyProperties(careReceiverEntity, careReceiverDetails);

        careReceiverDetails.setInstitutionName(careReceiverEntity.getInstitutionEntity().getName());

        return careReceiverDetails;
    }
}
