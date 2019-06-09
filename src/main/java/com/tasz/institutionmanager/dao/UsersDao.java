package com.tasz.institutionmanager.dao;

import com.tasz.institutionmanager.model.UsersDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsersDao extends CrudRepository<UsersDto, Integer> {
    UsersDto findByUsername(final String username);

    @Modifying
    @Query("update UsersDto user set user.password = :password where user.username = :username")
    int setPassword(@Param("username") String username, @Param("password") String password);
}
