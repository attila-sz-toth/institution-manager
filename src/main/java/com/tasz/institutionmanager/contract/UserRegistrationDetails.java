package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.Role;
import lombok.Data;

@Data
public class UserRegistrationDetails {
    private String username;
    private String name;
    private Role role;
    private String institutionName;
}
