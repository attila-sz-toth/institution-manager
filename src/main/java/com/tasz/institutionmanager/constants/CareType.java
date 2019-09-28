package com.tasz.institutionmanager.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CareType {
    IDOSEK_OTTHONA(0),
    HAZI_SEGITSEG_NYUJTAS(1),
    CSALADI_BOLCSODE(2),
    SZENVEDELYBETEGEK_REHABILITACIOJA(3),
    CSALADOK_ATMENETI_OTTHONA(4),
    KULSO_FEROHELY(5),
    OTTHONT_NYUJTO_ELLATAS(6),
    KULONLEGES_ELLATAS(7),
    UTOGONDOZOI_ELLATAS(8);

    private Integer id;
}
