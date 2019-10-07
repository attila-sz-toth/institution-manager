package com.tasz.institutionmanager.contract;

import lombok.Data;

import java.util.List;

@Data
public class UserDetails {
    private String username;
    private String role;
    private List<String> institutions;
}
