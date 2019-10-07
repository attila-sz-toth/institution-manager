package com.tasz.institutionmanager.contract;

import lombok.Data;

@Data
public class UserRegistrationDetails {
    private String username;
    private String role;
    private String institutionName;
}
