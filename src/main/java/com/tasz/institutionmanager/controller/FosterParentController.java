package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.FosterParentDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.service.FosterParentsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/foster-parents")
@CrossOrigin(origins = "http://localhost:3000")
public class FosterParentController {

    private FosterParentsService fosterParentsService;

    @GetMapping(value = "/get-parent-details", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FosterParentDetails getFosterParentDetails(final PersonalDetailsCompositeKey compositeKey) {
        log.info("Getting foster parent details of {}", compositeKey);
        return fosterParentsService.getFosterParentDetails(compositeKey);
    }

    @GetMapping(value = "/get-parent-list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<FosterParentDetails> getFosterParentsDetailsList(final PersonalDetailsCompositeKey childCompositeKey) {
        log.info("Getting foster parent list of child {}", childCompositeKey);
        return fosterParentsService.getFosterParentsDetailsList(childCompositeKey);
    }

    @PostMapping(value = "/add-parent", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addFosterParentDetails(final FosterParentDetails fosterParentDetails) {
        log.info("Adding new foster parent {}", fosterParentDetails);
        fosterParentsService.addFosterParentDetails(fosterParentDetails);
    }

    @PutMapping(value = "/update-parent-details", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateFosterParentDetails(final FosterParentDetails fosterParentDetails) {
        log.info("Update foster parent details {}", fosterParentDetails);
        fosterParentsService.updateFosterParentDetails(fosterParentDetails);
    }

    @DeleteMapping(value = "/delete-parent", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeFosterParentDetails(final PersonalDetailsCompositeKey compositeKey) {
        log.info("Delete foster parent {}", compositeKey);
        fosterParentsService.removeFosterParentDetails(compositeKey);
    }
}
