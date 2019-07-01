package com.tasz.institutionmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles", schema = "institutionm")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;
}
