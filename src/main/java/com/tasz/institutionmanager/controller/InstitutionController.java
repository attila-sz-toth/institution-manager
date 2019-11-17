package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.constants.CareType;
import com.tasz.institutionmanager.constants.InstitutionType;
import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.service.InstitutionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/institutions")
public class InstitutionController {

    private InstitutionService institutionService;

    @GetMapping(value = "/get-institutions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstitutionDetails> getInstitutions() {
        log.info("Getting all institutions");
        return institutionService.getInstitutions();
    }

    @GetMapping(value = "/get-institution-names", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getInstitutionNames() {
        log.info("Getting institution names");
        return institutionService.getInstitutionNames();
    }

    @GetMapping(value = "/get-institution-details/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InstitutionDetails getInstitutionDetails(@PathVariable("institution-name") final String institutionName) {
        log.info("Getting institution details of {}", institutionName);
        return institutionService.getInstitutionDetails(institutionName);
    }

    @PostMapping(value = "/add-institution", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addInstitution(@RequestBody InstitutionDetails institutionDetails) {
        log.info("Adding new institution {}", institutionDetails);
        institutionService.addInstitution(institutionDetails);
    }

    @DeleteMapping(value = "/delete-institution/{institution-name}")
    public void deleteInstitution(@PathVariable("institution-name") final String institutionName) {
        log.info("Deleting institution {}", institutionName);
        institutionService.deleteInstitution(institutionName);
    }

    @PutMapping(value = "/update-institution/{institution-name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRequest(@PathVariable("institution-name") final String institutionName, @RequestBody final InstitutionDetails institutionDetails) {
        log.info("Updating institution details of {}", institutionName);
        institutionService.updateInstitution(institutionName, institutionDetails);

    }

    @PostMapping(value = "/add-care-type/{institution-name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCareType(@PathVariable("institution-name") final String institutionName, @RequestBody final Set<CareType> careTypes) {
        log.info("Adding care type {} from institution {}", careTypes, institutionName);
        institutionService.addCareTypes(institutionName, careTypes);
    }

    @DeleteMapping(value = "/delete-care-type/{institution-name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCareType(@PathVariable("institution-name") final String institutionName, @RequestBody final Set<CareType> careTypes) {
        log.info("Deleting care type {} from institution {}", careTypes, institutionName);
        institutionService.deleteCareTypes(institutionName, careTypes);
    }

    @GetMapping(value = "/get-institution-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstitutionType> getInstitutionTypes() {
        return Arrays.asList(InstitutionType.values());
    }

    @GetMapping(value = "/get-care-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CareType> getCareTypes() {
        return Arrays.asList(CareType.values());
    }
}
