package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.service.CareReceiverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/care-receiver")
@CrossOrigin(origins = "http://localhost:3000")
public class CareReceiverController {

    private CareReceiverService careReceiverService;

    @GetMapping(value = "/get-all/{page-number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getCareReceivers(@PathVariable(name = "page-number") final Integer pageNumber) {
        log.info("Getting all care receivers...");
        return careReceiverService.getCareReceivers(pageNumber);
    }

    @GetMapping(value = "/get-by-institution/{page-number}/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getCareReceiversByInstitution(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                   @PathVariable(name = "institution-name") final String institutionName) {
        log.info("Getting care receivers of {}", institutionName);
        return careReceiverService.getCareReceiversByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-waiting/{page-number}/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getWaitingListByInstitution(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                 @PathVariable(name = "institution-name") final String institutionName) {
        log.info("Getting care receivers of {}", institutionName);
        return careReceiverService.getWaitingListByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-suspended/{page-number}/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getSuspendedListByInstitution(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                   @PathVariable(name = "institution-name") final String institutionName) {
        log.info("Getting care receivers of {}", institutionName);
        return careReceiverService.getSuspendedListByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-terminated/{page-number}/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getTerminatedListByInstitution(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                    @PathVariable(name = "institution-name") final String institutionName) {
        log.info("Getting care receivers of {}", institutionName);
        return careReceiverService.getTerminatedListByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-by-first-name/{page-number}/{first-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getCareReceiversByFirstName(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                 @PathVariable(name = "first-name") final String firstName) {
        log.info("Getting care receivers with first name {}", firstName);
        return careReceiverService.getCareReceiversByFirstName(pageNumber, firstName);
    }

    @GetMapping(value = "/get-by-last-name/{page-number}/{last-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getCareReceiversByLastName(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                @PathVariable(name = "last-name") final String lastName) {
        log.info("Getting care receivers with last name {}", lastName);
        return careReceiverService.getCareReceiversByLastName(pageNumber, lastName);
    }

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CareReceiverDetails getCareReceiver(@RequestBody final PersonalDetailsCompositeKey compositeKey) {
        log.info("Getting care receiver {}", compositeKey);
        return careReceiverService.getCareReceiver(compositeKey);
    }

    @PostMapping(value = "/add-care-receiver", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addCareReceiver(@RequestBody final CareReceiverDetails careReceiverDetails) {
        log.info("Adding new care receiver {}", careReceiverDetails);
        careReceiverService.addCareReceiver(careReceiverDetails);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCareReceiver(@RequestBody final CareReceiverDetails careReceiverDetails) {
        log.info("Updating care receiver {}", careReceiverDetails);
        careReceiverService.updateCareReceiver(careReceiverDetails);
    }


    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCareReceiver(@RequestBody final PersonalDetailsCompositeKey compositeKey) {
        log.info("Deleting care receiver {}", compositeKey);
        careReceiverService.deleteCareReceiver(compositeKey);
    }
}
