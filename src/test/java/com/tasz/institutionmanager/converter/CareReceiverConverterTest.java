package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.constants.Sex;
import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.tasz.institutionmanager.TestDataProvider.getCareReceiverEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


public class CareReceiverConverterTest {
    private final CareReceiverConverter careReceiverConverter = new CareReceiverConverter();
    private final Date now = new Date();
    private final InstitutionEntity institutionEntity = new InstitutionEntity();

    @Before
    public void setUp() {
        institutionEntity.setName("institution-name");
    }

    @Test
    public void convert_FullData() {
        final CareReceiverEntity entity = getCareReceiverEntity(now);

        final CareReceiverDetails result = careReceiverConverter.convert(entity);

        assertThat(result.getAddress(), is("test_address"));
        assertThat(result.getBirthDate(), is(now));
        assertThat(result.getBirthName(), is("birth_name"));
        assertThat(result.getBirthPlace(), is("birth_place"));
        assertThat(result.getCareStatus(), is(CareStatus.ACTIVE));
        assertThat(result.getEmail(), is("e_mail"));
        assertThat(result.getEndOfCare(), is(now));
        assertThat(result.getFirstName(), is("first_name"));
        assertThat(result.getInstitutionName(), is("test_institution"));
        assertThat(result.getLastName(), is("last_name"));
        assertThat(result.getMothersName(), is("mothers_name"));
        assertThat(result.getPhoneNumber(), is("phone_number"));
        assertThat(result.getSex(), is(Sex.FERFI));
        assertThat(result.getStartOfCare(), is(now));
    }

    @Test
    public void convert_Null() {
        final CareReceiverDetails result = careReceiverConverter.convert(null);

        assertThat(result, nullValue());
    }
}