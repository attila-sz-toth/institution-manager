package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.Normative;
import com.tasz.institutionmanager.service.NormativeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/normative")
public class NormativeController {
    private final NormativeService normativeService;

    @GetMapping(value = "/get-normative-list/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Normative> getNormativeByInstitution(@PathVariable("institution-name") final String institutionName) {
        return normativeService.getNormativeByInstitution(institutionName);
    }

    @PostMapping(value = "/add-normative/{institution-name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewNormative(@PathVariable("institution-name") final String institutionName,
                                @RequestBody final Normative normative) {
        normativeService.addNewNormative(institutionName, normative);
    }

    @PutMapping(value = "/update-normative/{institution-name}/{year}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateNormativeAmount(@PathVariable("institution-name") final String institutionName,
                                      @PathVariable("year") final String year,
                                      @RequestBody final Integer amount) {
        normativeService.updateNormativeAmount(institutionName, year, amount);
    }

    @DeleteMapping(value = "/delete-normative/{institution-name}/{year}")
    public void deleteNormative(@PathVariable("institution-name") final String institutionName,
                                @PathVariable("year") final String year) {
        normativeService.deleteNormative(institutionName, year);
    }
}
