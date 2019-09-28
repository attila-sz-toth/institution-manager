package com.tasz.institutionmanager.contract;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalDetailsCompositeKey {
    private String firstName;
    private String lastName;
    private String mothersName;
    private Date birthDate;
}
