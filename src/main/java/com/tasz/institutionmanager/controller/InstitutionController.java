package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.InstitutionDetails;
import com.tasz.institutionmanager.service.InstitutionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/institutions")
public class InstitutionController {

    private InstitutionService institutionService;

    @GetMapping(value = "/get-institutions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstitutionDetails> getInstitutions() {
        log.info("Getting all institutions");
        return institutionService.getInstitutions();
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
}
