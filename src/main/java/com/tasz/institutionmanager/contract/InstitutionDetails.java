package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.CareType;
import lombok.Data;

import java.util.Set;

@Data
public class InstitutionDetails {
    private String name;
    private String address;
    private String institutionType;
    private Set<CareType> careTypes;
}
