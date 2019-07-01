package com.tasz.institutionmanager.repository;

import com.tasz.institutionmanager.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(final String username);

    @Modifying
    @Query("update User user set user.password = :password where user.username = :username")
    int setPassword(@Param("username") String username, @Param("password") String password);
}
