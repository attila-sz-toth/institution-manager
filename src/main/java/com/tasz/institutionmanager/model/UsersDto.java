package com.tasz.institutionmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "institutionm")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersDto {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "seq_user_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RolesDto role;
}
