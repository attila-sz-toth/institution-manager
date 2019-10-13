package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.Role;
import lombok.Data;

@Data
public class UserDetails {
    private String username;
    private String name;
    private Role role;
    private String institution;
}
