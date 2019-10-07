package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.InstitutionCareTypeEntity;
import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionCareTypeRepository extends JpaRepository<InstitutionCareTypeEntity, Integer> {
    void deleteByCareTypeAndInstitutionEntity(final String careType, final InstitutionEntity institutionEntity);
}
