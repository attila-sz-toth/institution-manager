package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.Normative;
import com.tasz.institutionmanager.model.NormativeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NormativeEntityConverter implements Converter<Normative, NormativeEntity> {
    @Override
    public NormativeEntity convert(Normative normative) {
        final NormativeEntity normativeEntity = new NormativeEntity();
        BeanUtils.copyProperties(normative, normativeEntity);
        return normativeEntity;
    }
}
