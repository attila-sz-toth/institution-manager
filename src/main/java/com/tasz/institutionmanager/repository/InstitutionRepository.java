package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, Integer> {
    Institution findByName(final String institutionName);
}
