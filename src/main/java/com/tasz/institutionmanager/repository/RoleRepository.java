package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRoleName(final String role);
}
