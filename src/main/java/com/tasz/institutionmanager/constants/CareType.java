package com.tasz.institutionmanager.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum CareType {
    IDOSEK_OTTHONA("Idősek Otthona"),
    HAZI_SEGITSEG_NYUJTAS("Házi Segítségnyújtás"),
    CSALADI_BOLCSODE("Családi Bölcsőde"),
    SZENVEDELYBETEGEK_REHABILITACIOJA("Szenvedélybetegek Rehabilitációja"),
    CSALADOK_ATMENETI_OTTHONA("Családok Átmeneti Otthona"),
    KULSO_FEROHELY("Külső Férőhely"),
    OTTHONT_NYUJTO_ELLATAS("Otthont Nyújtó Ellátás"),
    KULONLEGES_ELLATAS("Különleges Ellátás"),
    UTOGONDOZOI_ELLATAS("Utógondozói Ellátás");

    private final String value;
    private final String description;

    CareType(String description) {
        this.value = name();
        this.description = description;
    }
}
