package com.tasz.institutionmanager.contract;

import com.tasz.institutionmanager.constants.CareStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CareReceiverDetails extends PersonalDetails {
    private CareStatus careStatus;
    private String institutionName;
    private Integer taj;
    private Date startOfCare; //optional
    private Date endOfCare; //optional
    private Set<PersonalDetails> closeRelatives;
}
