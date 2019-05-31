package com.tasz.institutionmanager.dao;

import com.tasz.institutionmanager.model.UsersDto;
import org.springframework.data.repository.CrudRepository;

public interface UsersDao extends CrudRepository<UsersDto, String> {
}
