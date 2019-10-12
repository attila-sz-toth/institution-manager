package com.tasz.institutionmanager.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum InstitutionType {
    IDOSEK_OTTHONA("Idősek Otthona"),
    CSALADI_BOLCSODE("Családi Bölcsőde"),
    REHABILITACIO("Rehabilitáció"),
    HAZI_SEGITSEGNYUJTAS("Házi Segítségnyújtás"),
    NEVELOSZULOI_HALOZAT("Nevelőszülői Hálózat");

    private final String value;
    private final String description;

    InstitutionType(String description) {
        this.value = name();
        this.description = description;
    }
}
