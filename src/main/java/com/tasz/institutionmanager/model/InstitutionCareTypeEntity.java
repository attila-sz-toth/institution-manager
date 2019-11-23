package com.tasz.institutionmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "INSTITUTION_CARE_TYPES", schema = "institution_manager")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstitutionCareTypeEntity {
    @Id
    @SequenceGenerator(
            name = "institution_id_generator",
            sequenceName = "institution_manager.seq_institution_care_type_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institution_id_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "care_type")
    private String careType;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionEntity institutionEntity;
}
