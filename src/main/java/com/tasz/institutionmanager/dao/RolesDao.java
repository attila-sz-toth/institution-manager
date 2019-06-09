package com.tasz.institutionmanager.dao;

import com.tasz.institutionmanager.constants.Role;
import com.tasz.institutionmanager.model.RolesDto;
import org.springframework.data.repository.CrudRepository;

public interface RolesDao extends CrudRepository<RolesDto, Integer> {
    RolesDto findByRole(final Role role);
}
