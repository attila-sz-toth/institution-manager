package com.tasz.institutionmanager.model;

import com.tasz.institutionmanager.constants.InstitutionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "institutions", schema = "institutionm")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstitutionEntity {
    @Id
    @SequenceGenerator(name = "institution_id_generator", sequenceName = "seq_institution_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institution_id_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private InstitutionType type;

    @OneToMany(mappedBy = "institutionEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<InstitutionCareTypeEntity> careTypes = new HashSet<>();

    @OneToMany(mappedBy = "institutionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CareReceiverEntity> careReceiverEntities = new HashSet<>();

//    @ManyToMany(mappedBy = "institutionEntitySet")
//    private Set<UserEntity> userList;
}
