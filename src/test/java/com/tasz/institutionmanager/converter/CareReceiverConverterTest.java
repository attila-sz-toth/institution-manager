package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.constants.Sex;
import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CareReceiverConverterTest {
    private final CareReceiverConverter careReceiverConverter = new CareReceiverConverter();
    private final Date now = new Date();
    private final InstitutionEntity institutionEntity = new InstitutionEntity();

    @Before
    public void setUp() throws Exception {
        institutionEntity.setName("institution-name");
    }

    @Test
    public void convert() {
        final CareReceiverEntity entity = new CareReceiverEntity();
        entity.setAddress("address");
        entity.setBirthDate(now);
        entity.setBirthName("birth-name");
        entity.setBirthPlace("birth-place");
        entity.setCareStatus(CareStatus.ACTIVE);
        entity.setEmail("e-mail");
        entity.setEndOfCare(now);
        entity.setFirstName("first-name");
        entity.setId(0);
        entity.setInstitutionEntity(institutionEntity);
        entity.setLastName("last-name");
        entity.setMothersName("mothers-name");
        entity.setPhoneNumber("phone-number");
        entity.setSex(Sex.FERFI);
        entity.setStartOfCare(now);

        final CareReceiverDetails result = careReceiverConverter.convert(entity);

        assertThat(result.getAddress(), is("address"));
        assertThat(result.getBirthDate(), is(now));
        assertThat(result.getBirthName(), is("birth-name"));
        assertThat(result.getBirthPlace(), is("birth-place"));
        assertThat(result.getCareStatus(), is(CareStatus.ACTIVE));
        assertThat(result.getEmail(), is("e-mail"));
        assertThat(result.getEndOfCare(), is(now));
        assertThat(result.getFirstName(), is("first-name"));
        assertThat(result.getInstitutionName(), is("institution-name"));
        assertThat(result.getLastName(), is("last-name"));
        assertThat(result.getMothersName(), is("mothers-name"));
        assertThat(result.getPhoneNumber(), is("phone-number"));
        assertThat(result.getSex(), is(Sex.FERFI));
        assertThat(result.getStartOfCare(), is(now));
    }
}