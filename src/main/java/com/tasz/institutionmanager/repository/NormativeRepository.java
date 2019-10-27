package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.InstitutionEntity;
import com.tasz.institutionmanager.model.NormativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormativeRepository extends JpaRepository<NormativeEntity, Integer> {
    NormativeEntity findByInstitutionEntityAndYear(final InstitutionEntity institutionEntity, final String year);

    List<NormativeEntity> findByInstitutionEntity(final InstitutionEntity institutionEntity);
}
