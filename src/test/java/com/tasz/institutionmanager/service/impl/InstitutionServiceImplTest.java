package com.tasz.institutionmanager.service.impl;

import com.tasz.institutionmanager.TestDataProvider;
import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.converter.InstitutionDetailsConverter;
import com.tasz.institutionmanager.converter.InstitutionEntityConverter;
import com.tasz.institutionmanager.repository.InstitutionCareTypeRepository;
import com.tasz.institutionmanager.repository.InstitutionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InstitutionServiceImplTest {

    private InstitutionServiceImpl institutionService;

    private InstitutionDetailsConverter institutionDetailsConverter = new InstitutionDetailsConverter();
    private InstitutionEntityConverter institutionEntityConverter = new InstitutionEntityConverter();

    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private InstitutionCareTypeRepository institutionCareTypeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        institutionService = new InstitutionServiceImpl(
                institutionRepository,
                institutionCareTypeRepository,
                institutionDetailsConverter,
                institutionEntityConverter
        );
    }

    @Test
    public void addCareTypes_Existing() {
        final HashSet<CareType> careTypes = new HashSet<>();
        careTypes.add(CareType.IDOSEK_OTTHONA);
        careTypes.add(CareType.HAZI_SEGITSEG_NYUJTAS);

        when(institutionRepository.findByName("test_institution"))
                .thenReturn(TestDataProvider.getInstitutionEntity());

        institutionService.addCareTypes("test_institution", careTypes);

        verify(institutionCareTypeRepository, times(0)).save(any());
    }

    @Test
    public void addCareTypes_New() {
        final HashSet<CareType> careTypes = new HashSet<>();
        careTypes.add(CareType.CSALADI_BOLCSODE);
        careTypes.add(CareType.KULSO_FEROHELY);
        careTypes.add(CareType.IDOSEK_OTTHONA);

        when(institutionRepository.findByName("test_institution"))
                .thenReturn(TestDataProvider.getInstitutionEntity());

        institutionService.addCareTypes("test_institution", careTypes);

        verify(institutionCareTypeRepository, times(2)).save(any());
    }

    @Test
    public void addCareTypes_Empty() {
        final HashSet<CareType> careTypes = new HashSet<>();

        when(institutionRepository.findByName("test_institution"))
                .thenReturn(TestDataProvider.getInstitutionEntity());

        institutionService.addCareTypes("test_institution", careTypes);

        verify(institutionCareTypeRepository, times(0)).save(any());
    }

    @Test
    public void deleteCareTypes_Valid() {
        final HashSet<CareType> careTypes = new HashSet<>();
        careTypes.add(CareType.IDOSEK_OTTHONA);

        when(institutionRepository.findByName("test_institution"))
                .thenReturn(TestDataProvider.getInstitutionEntity());

        institutionService.deleteCareTypes("test_institution", careTypes);

        verify(institutionCareTypeRepository, times(1)).deleteByCareTypeAndInstitutionEntity(any(), any());
    }

    @Test
    public void deleteCareTypes_Empty() {
        final HashSet<CareType> careTypes = new HashSet<>();

        when(institutionRepository.findByName("test_institution"))
                .thenReturn(TestDataProvider.getInstitutionEntity());

        institutionService.deleteCareTypes("test_institution", careTypes);

        verify(institutionCareTypeRepository, times(0)).deleteByCareTypeAndInstitutionEntity(any(), any());
    }
}