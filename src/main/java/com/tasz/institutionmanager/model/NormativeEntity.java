package com.tasz.institutionmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "normative", schema = "institutionm")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NormativeEntity {
    @Id
    @SequenceGenerator(name = "normative_id_generator", sequenceName = "institutionm.seq_normative_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "normative_id_generator")
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionEntity institutionEntity;

    @Column(name = "year")
    private String year;

    @Column(name = "amount")
    private Integer amount;
}
