package com.tasz.institutionmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "institutions", schema = "institutionm")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Institution {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_id")
    private Integer typeId;

    @ManyToMany(mappedBy = "institutionSet")
    private Set<User> userList;
}
