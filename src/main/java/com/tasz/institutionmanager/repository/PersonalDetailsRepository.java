package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.PersonalDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetailsEntity, Integer> {
}
