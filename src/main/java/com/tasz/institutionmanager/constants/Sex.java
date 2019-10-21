package com.tasz.institutionmanager.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Sex {
    FERFI("Férfi"),
    NO("Nő");

    private final String value;
    private final String description;

    Sex(String description) {
        this.value = name();
        this.description = description;
    }
}
