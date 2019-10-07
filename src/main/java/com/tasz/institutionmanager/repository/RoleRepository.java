package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findByRoleName(final String role);
}
