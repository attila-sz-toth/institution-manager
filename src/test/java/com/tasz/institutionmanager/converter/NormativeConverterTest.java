package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.contract.Normative;
import com.tasz.institutionmanager.model.NormativeEntity;
import org.junit.Test;

import static com.tasz.institutionmanager.TestDataProvider.getInstitutionEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class NormativeConverterTest {

    private final NormativeConverter normativeConverter = new NormativeConverter();

    @Test
    public void convert_FullData() {
        final NormativeEntity normativeEntity = new NormativeEntity();
        normativeEntity.setId(0);
        normativeEntity.setYear("2020");
        normativeEntity.setAmount(42);
        normativeEntity.setInstitutionEntity(getInstitutionEntity());

        final Normative normative = normativeConverter.convert(normativeEntity);

        assertThat(normative.getYear(), is("2020"));
        assertThat(normative.getAmount(), is(42));
    }

    @Test
    public void convert_Null() {
        final Normative normative = normativeConverter.convert(null);

        assertThat(normative, nullValue());
    }
}