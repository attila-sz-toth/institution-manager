package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(final String username);

    void deleteUserByUsername(final String username);

    @Modifying
    @Query("update UserEntity user set user.password = :password where user.username = :username")
    int setPassword(@Param("username") String username, @Param("password") String password);
}
