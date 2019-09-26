package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.CareStatus;
import lombok.Data;

@Data
public class PersonalDetails {
    private PersonalDetailsCompositeKey compositeKey;
    private CareStatus careStatus;
}
