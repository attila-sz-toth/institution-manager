package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.constants.InstitutionType;
import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.model.InstitutionCareTypeEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstitutionDetailsConverterTest {

    private final InstitutionDetailsConverter institutionDetailsConverter = new InstitutionDetailsConverter();

    @Test
    public void convert() {
        final InstitutionCareTypeEntity institutionCareTypeEntity = new InstitutionCareTypeEntity();
        final InstitutionEntity institutionEntity = new InstitutionEntity();

        institutionCareTypeEntity.setId(42);
        institutionCareTypeEntity.setCareType(CareType.IDOSEK_OTTHONA.name());
        institutionCareTypeEntity.setInstitutionEntity(institutionEntity);

        institutionEntity.setId(42);
        institutionEntity.setName("institution-name");
        institutionEntity.setType(InstitutionType.IDOSEK_OTTHONA);
        institutionEntity.setAddress("address");
        institutionEntity.setCareTypes(setOf(institutionCareTypeEntity));
        institutionEntity.setCareReceiverEntities(null);

        final InstitutionDetails institutionDetails = institutionDetailsConverter.convert(institutionEntity);

        assertThat(institutionDetails.getName(), is("institution-name"));
        assertThat(institutionDetails.getType(), is(InstitutionType.IDOSEK_OTTHONA));
        assertThat(institutionDetails.getAddress(), is("address"));
        assertThat(institutionDetails.getCareTypes(), is(setOf(CareType.IDOSEK_OTTHONA)));
    }

    @SafeVarargs
    private final <T> Set<T> setOf(T... args) {
        return Arrays.stream(args)
                .collect(Collectors.toSet());
    }
}