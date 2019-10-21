package com.tasz.institutionmanager.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tasz.institutionmanager.constants.Sex;
import com.tasz.institutionmanager.serializer.DateDeserializer;
import com.tasz.institutionmanager.serializer.DateSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class PersonalDetails {
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mothersName;
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date birthDate;
    private String birthName;
    private String birthPlace;
    private Sex sex;
    private String address;
    private String phoneNumber; //optional
    private String email; //optional
}
