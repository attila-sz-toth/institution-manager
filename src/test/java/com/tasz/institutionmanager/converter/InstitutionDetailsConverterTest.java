package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.constants.InstitutionType;
import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static com.tasz.institutionmanager.TestDataProvider.getInstitutionEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstitutionDetailsConverterTest {
    private final InstitutionDetailsConverter institutionDetailsConverter = new InstitutionDetailsConverter();

    @Test
    public void convert_FullData() {
        final InstitutionEntity institutionEntity = getInstitutionEntity();

        final InstitutionDetails institutionDetails = institutionDetailsConverter.convert(institutionEntity);

        assertThat(institutionDetails.getName(), is("test_institution"));
        assertThat(institutionDetails.getAddress(), is("test_address"));
        assertThat(institutionDetails.getType(), is(InstitutionType.IDOSEK_OTTHONA));
        assertThat(institutionDetails.getCareTypes(), CoreMatchers.hasItem(CareType.IDOSEK_OTTHONA));
        assertThat(institutionDetails.getCareTypes(), CoreMatchers.hasItem(CareType.HAZI_SEGITSEG_NYUJTAS));
    }

    @Test
    public void convert_Null() {
        final InstitutionDetails institutionDetails = institutionDetailsConverter.convert(null);

        assertThat(institutionDetails, nullValue());
    }
}