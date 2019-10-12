package com.tasz.institutionmanager.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(0, "Adminisztrátor"),
    EMPLOYEE(1, "Alkalmazott");

    private Integer id;
    private String description;
}