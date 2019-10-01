package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.InstitutionCareType;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionCareTypeRepository extends JpaRepository<InstitutionCareType, Integer> {
    public void deleteByCareTypeAndInstitutionEntity(final String careType, final InstitutionEntity institutionEntity);
}
