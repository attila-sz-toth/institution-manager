package com.tasz.institutionmanager.model;

import com.tasz.institutionmanager.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDto {
    @Id
    private String username;

    private String password;

    private Role role;
}
