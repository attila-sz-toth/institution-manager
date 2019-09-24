package com.tasz.institutionmanager.contract;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class UserDetails {
    private final String username;
    private final String role;
    private final List<String> institutions;
}
