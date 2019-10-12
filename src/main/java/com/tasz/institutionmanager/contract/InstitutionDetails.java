package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.constants.InstitutionType;
import lombok.Data;

import java.util.Set;

@Data
public class InstitutionDetails {
    private String name;
    private String address;
    private InstitutionType type;
    private Set<CareType> careTypes;
}
