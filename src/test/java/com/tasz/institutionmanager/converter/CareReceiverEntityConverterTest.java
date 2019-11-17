package com.tasz.institutionmanager.converter;

import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.constants.Sex;
import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

public class CareReceiverEntityConverterTest {
    private final Date now = new Date();

    @Mock
    private InstitutionRepository institutionRepository;

    private CareReceiverEntityConverter careReceiverEntityConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        careReceiverEntityConverter = new CareReceiverEntityConverter(institutionRepository);
    }

    @Test
    public void convert() {
        final InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setName("institution-name");
        institutionEntity.setId(42);

        when(institutionRepository.findByName("institution-name"))
                .thenReturn(institutionEntity);

        final CareReceiverDetails careReceiverDetails = new CareReceiverDetails();
        careReceiverDetails.setAddress("address");
        careReceiverDetails.setBirthDate(now);
        careReceiverDetails.setBirthName("birth-name");
        careReceiverDetails.setBirthPlace("birth-place");
        careReceiverDetails.setCareStatus(CareStatus.ACTIVE);
        careReceiverDetails.setEmail("e-mail");
        careReceiverDetails.setEndOfCare(now);
        careReceiverDetails.setFirstName("first-name");
        careReceiverDetails.setInstitutionName("institution-name");
        careReceiverDetails.setLastName("last-name");
        careReceiverDetails.setMothersName("mothers-name");
        careReceiverDetails.setPhoneNumber("phone-number");
        careReceiverDetails.setSex(Sex.FERFI);
        careReceiverDetails.setStartOfCare(now);

        final CareReceiverEntity result = careReceiverEntityConverter.convert(careReceiverDetails);

        assertThat(result.getAddress(), is("address"));
        assertThat(result.getBirthDate(), is(now));
        assertThat(result.getBirthName(), is("birth-name"));
        assertThat(result.getBirthPlace(), is("birth-place"));
        assertThat(result.getCareStatus(), is(CareStatus.ACTIVE));
        assertThat(result.getEmail(), is("e-mail"));
        assertThat(result.getEndOfCare(), is(now));
        assertThat(result.getFirstName(), is("first-name"));
        assertThat(result.getId(), nullValue());
        assertThat(result.getInstitutionEntity(), is(institutionEntity));
        assertThat(result.getLastName(), is("last-name"));
        assertThat(result.getMothersName(), is("mothers-name"));
        assertThat(result.getPhoneNumber(), is("phone-number"));
        assertThat(result.getSex(), is(Sex.FERFI));
        assertThat(result.getStartOfCare(), is(now));
    }
}