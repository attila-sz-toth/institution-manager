package com.tasz.institutionmanager.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CareType {
    @JsonProperty("IDOSEK_OTTHONA")
    IDOSEK_OTTHONA,
    @JsonProperty("HAZI_SEGITSEG_NYUJTAS")
    HAZI_SEGITSEG_NYUJTAS,
    @JsonProperty("CSALADI_BOLCSODE")
    CSALADI_BOLCSODE,
    @JsonProperty("SZENVEDELYBETEGEK_REHABILITACIOJA")
    SZENVEDELYBETEGEK_REHABILITACIOJA,
    @JsonProperty("CSALADOK_ATMENETI_OTTHONA")
    CSALADOK_ATMENETI_OTTHONA,
    @JsonProperty("KULSO_FEROHELY")
    KULSO_FEROHELY,
    @JsonProperty("OTTHONT_NYUJTO_ELLATAS")
    OTTHONT_NYUJTO_ELLATAS,
    @JsonProperty("KULONLEGES_ELLATAS")
    KULONLEGES_ELLATAS,
    @JsonProperty("UTOGONDOZOI_ELLATAS")
    UTOGONDOZOI_ELLATAS
}
