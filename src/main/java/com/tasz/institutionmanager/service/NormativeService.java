package com.tasz.institutionmanager.service;

import com.tasz.institutionmanager.contract.Normative;

import java.util.List;

public interface NormativeService {
    List<Normative> getNormativeByInstitution(final String institutionName);

    void addNewNormative(final String institutionName, final Normative normative);

    void updateNormativeAmount(final String institutionName, final String year, final Integer amount);

    void deleteNormative(final String institutionName, final String year);
}
