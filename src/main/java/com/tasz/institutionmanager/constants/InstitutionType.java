package com.tasz.institutionmanager.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InstitutionType {
    IDOSEK_OTTHONA(0),
    CSALADI_BOLCSODE(1),
    REHABILITACIO(2),
    HAZI_SEGITSEGNYUJTAS(3),
    NEVELOSZULOI_HALOZAT(4);

    private Integer id;
}
