package com.tasz.institutionmanager.model;

import com.tasz.institutionmanager.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personal_details", schema = "institution_manager")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalDetailsEntity {
    @Id
    @SequenceGenerator(
            name = "personal_details_id_generator",
            schema = "institution_manager",
            sequenceName = "seq_personal_details_id",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_details_id_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mothers_name")
    private String mothersName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_name")
    private String birthName;

    @Column(name = "birth_place")
    private String birthPlace;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}
