package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.TestDataProvider;
import com.tasz.institutionmanager.converter.CareReceiverConverter;
import com.tasz.institutionmanager.converter.CareReceiverEntityConverter;
import com.tasz.institutionmanager.model.CareReceiverEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.repository.CareReceiverRepository;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import com.tasz.institutionmanager.repository.NormativeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.tasz.institutionmanager.TestDataProvider.getInstitutionEntity;
import static com.tasz.institutionmanager.TestDataProvider.getNormativeEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class CareReceiverServiceImplTest {

    private CareReceiverServiceImpl careReceiverService;
    private CareReceiverEntityConverter careReceiverEntityConverter;

    private CareReceiverConverter careReceiverConverter = new CareReceiverConverter();

    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private CareReceiverRepository careReceiverRepository;
    @Mock
    private NormativeRepository normativeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        careReceiverEntityConverter = new CareReceiverEntityConverter(institutionRepository);
        careReceiverService = new CareReceiverServiceImpl(
                institutionRepository,
                careReceiverRepository,
                normativeRepository,
                careReceiverConverter,
                careReceiverEntityConverter
        );
    }

    @Test
    public void normativeByInstitutionAndYear() throws ParseException {
        final InstitutionEntity institutionEntity = getInstitutionEntity();
        when(institutionRepository.findByName("test_institution"))
                .thenReturn(institutionEntity);

        when(normativeRepository.findByInstitutionEntityAndYear(institutionEntity, "2020"))
                .thenReturn(getNormativeEntity());

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date fromDate = dateFormat.parse("2020-01-01");
        final Date toDate = dateFormat.parse("2020-12-31");

        final Set<CareReceiverEntity> careReceiverEntities = new HashSet<>();
        careReceiverEntities.add(TestDataProvider.getCareReceiverEntity(new Date()));
        when(careReceiverRepository.findCareReceiversByInstitutionInTimeRange(institutionEntity, fromDate, toDate))
                .thenReturn(careReceiverEntities);

        final Integer normative = careReceiverService.normativeByInstitutionAndYear("test_institution", "2020");

        assertThat(normative, is(15330));
    }


}