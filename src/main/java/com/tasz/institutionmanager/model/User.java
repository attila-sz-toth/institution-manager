package com.tasz.institutionmanager.model;

import com.tasz.institutionmanager.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private Role role;
}
