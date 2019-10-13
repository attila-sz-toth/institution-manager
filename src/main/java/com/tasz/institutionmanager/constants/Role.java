package com.tasz.institutionmanager.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Role {
    ADMIN(0, "Adminisztrátor"),
    EMPLOYEE(1, "Alkalmazott");

    Role(Integer id, String description) {
        this.id = id;
        this.value = name();
        this.description = description;
    }

    private final Integer id;
    private final String value;
    private final String description;
}