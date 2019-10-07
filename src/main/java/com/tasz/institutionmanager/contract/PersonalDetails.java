package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.Sex;
import lombok.Data;

import java.util.Date;

@Data
public class PersonalDetails {
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mothersName;
    private Date birthDate;
    private String birthName;
    private String birthPlace;
    private Sex sex;
    private String address;
    private String phoneNumber; //optional
    private String email; //optional
}
