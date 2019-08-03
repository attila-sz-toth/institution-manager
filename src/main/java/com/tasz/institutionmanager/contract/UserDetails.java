package com.tasz.institutionmanager.contract;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class UserDetails {
    private final String username;
    private final String role;
}
