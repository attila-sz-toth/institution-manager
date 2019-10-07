package com.tasz.institutionmanager.model;

import com.tasz.institutionmanager.constants.CareStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "care_receiver", schema = "institutionm")
@PrimaryKeyJoinColumn(name = "personal_details_id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CareReceiverEntity extends PersonalDetailsEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "care_status")
    private CareStatus careStatus;

    @Column(name = "taj")
    private Integer taj;

    @Column(name = "start_of_care")
    private Date startOfCare;

    @Column(name = "end_of_care")
    private Date endOfCare;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionEntity institutionEntity;
}
