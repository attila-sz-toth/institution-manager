package com.tasz.institutionmanager.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CareStatus {
    ACTIVE(0),
    SUSPENDED(1),
    WAITING(2),
    TERMINATED(3);

    private Integer id;
}
