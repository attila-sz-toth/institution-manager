package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Integer> {
    InstitutionEntity findByName(final String institutionName);

    void deleteByName(final String institutionName);
}
