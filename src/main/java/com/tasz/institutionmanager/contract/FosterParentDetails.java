package com.tasz.institutionmanager.contract;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FosterParentDetails extends PersonalDetails {
    private Set<FosterChildDetails> fosterChildren;
}
