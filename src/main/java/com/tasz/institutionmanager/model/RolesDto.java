package com.tasz.institutionmanager.model;

import com.tasz.institutionmanager.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "institutionm")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RolesDto {
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Role role;
}
