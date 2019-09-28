package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.Sex;
import lombok.Data;

@Data
public class PersonalDetails {
    private PersonalDetailsCompositeKey compositeKey;
    private String title;
    private String middleName;
    private String birthName;
    private String birthPlace;
    private Sex sex;
    private String address;
    private String phoneNumber; //optional
    private String email; //optional
}
