package com.tasz.institutionmanager.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tasz.institutionmanager.constants.CareStatus;
import com.tasz.institutionmanager.serializer.DateDeserializer;
import com.tasz.institutionmanager.serializer.DateSerializer;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class CareReceiverDetails extends PersonalDetails {
    private CareStatus careStatus;
    private String institutionName;
    private Integer taj;
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date startOfCare; //optional
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date endOfCare; //optional
    private Set<PersonalDetails> closeRelatives;
}
