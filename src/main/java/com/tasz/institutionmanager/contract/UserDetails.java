package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDetails {
    private String username;
    private Role role;
    private List<String> institutions;
}
