package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.Normative;
import com.tasz.institutionmanager.model.NormativeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NormativeConverter implements Converter<NormativeEntity, Normative> {

    @Override
    public Normative convert(NormativeEntity normativeEntity) {
        if (normativeEntity == null) {
            return null;
        }

        final Normative normative = new Normative();
        BeanUtils.copyProperties(normativeEntity, normative);
        return normative;
    }
}
