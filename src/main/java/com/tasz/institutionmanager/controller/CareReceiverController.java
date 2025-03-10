package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.constants.Sex;
import com.tasz.institutionmanager.contract.CareReceiverDetails;
import com.tasz.institutionmanager.contract.PersonalDetailsCompositeKey;
import com.tasz.institutionmanager.service.CareReceiverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.tasz.institutionmanager.serializer.DateSerializer.DATE_FORMAT;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/care-receiver")
public class CareReceiverController {

    private CareReceiverService careReceiverService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

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
        log.info("Getting waiting list of {}", institutionName);
        return careReceiverService.getWaitingListByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-suspended/{page-number}/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getSuspendedListByInstitution(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                   @PathVariable(name = "institution-name") final String institutionName) {
        log.info("Getting suspended care receivers of {}", institutionName);
        return careReceiverService.getSuspendedListByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-terminated/{page-number}/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getTerminatedListByInstitution(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                    @PathVariable(name = "institution-name") final String institutionName) {
        log.info("Getting terminated care receivers of {}", institutionName);
        return careReceiverService.getTerminatedListByInstitution(pageNumber, institutionName);
    }

    @GetMapping(value = "/get-by-first-name/{page-number}/{first-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getCareReceiversByFirstName(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                 @PathVariable(name = "first-name") final String firstName) {
        log.info("Getting care receivers by first name {}", firstName);
        return careReceiverService.getCareReceiversByFirstName(pageNumber, firstName);
    }

    @GetMapping(value = "/get-by-last-name/{page-number}/{last-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CareReceiverDetails> getCareReceiversByLastName(@PathVariable(name = "page-number") final Integer pageNumber,
                                                                @PathVariable(name = "last-name") final String lastName) {
        log.info("Getting care receivers by last name {}", lastName);
        return careReceiverService.getCareReceiversByLastName(pageNumber, lastName);
    }

    @GetMapping(value = "/get/{first-name}/{last-name}/{mothers-name}/{birth-date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CareReceiverDetails getCareReceiver(
            @PathVariable("first-name") final String firstName,
            @PathVariable("last-name") final String lastName,
            @PathVariable("mothers-name") final String mothersName,
            @PathVariable("birth-date") final String birthDate) throws ParseException {

        final PersonalDetailsCompositeKey compositeKey = new PersonalDetailsCompositeKey();
        compositeKey.setFirstName(firstName);
        compositeKey.setLastName(lastName);
        compositeKey.setMothersName(mothersName);
        compositeKey.setBirthDate(dateFormat.parse(birthDate));

        log.info("Getting care receiver {}", compositeKey);
        return careReceiverService.getCareReceiver(compositeKey);
    }

    @PostMapping(value = "/add-care-receiver", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addCareReceiver(@RequestBody final CareReceiverDetails careReceiverDetails) {
        log.info("Adding new care receiver {}", careReceiverDetails);
        careReceiverService.addCareReceiver(careReceiverDetails);
    }

    @PutMapping(value = "/update/{first-name}/{last-name}/{mothers-name}/{birth-date}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCareReceiver(@PathVariable("first-name") final String firstName,
                                   @PathVariable("last-name") final String lastName,
                                   @PathVariable("mothers-name") final String mothersName,
                                   @PathVariable("birth-date") final String birthDate,
                                   @RequestBody final CareReceiverDetails careReceiverDetails) throws ParseException {

        final PersonalDetailsCompositeKey compositeKey = new PersonalDetailsCompositeKey();
        compositeKey.setFirstName(firstName);
        compositeKey.setLastName(lastName);
        compositeKey.setMothersName(mothersName);
        compositeKey.setBirthDate(dateFormat.parse(birthDate));

        log.info("Updating care receiver {}", careReceiverDetails);
        careReceiverService.updateCareReceiver(compositeKey, careReceiverDetails);
    }


    @DeleteMapping(value = "/delete/{first-name}/{last-name}/{mothers-name}/{birth-date}")
    public void deleteCareReceiver(@PathVariable("first-name") final String firstName,
                                   @PathVariable("last-name") final String lastName,
                                   @PathVariable("mothers-name") final String mothersName,
                                   @PathVariable("birth-date") final String birthDate) throws ParseException {

        final PersonalDetailsCompositeKey compositeKey = new PersonalDetailsCompositeKey();
        compositeKey.setFirstName(firstName);
        compositeKey.setLastName(lastName);
        compositeKey.setMothersName(mothersName);
        compositeKey.setBirthDate(dateFormat.parse(birthDate));

        log.info("Deleting care receiver {}", compositeKey);
        careReceiverService.deleteCareReceiver(compositeKey);
    }

    @GetMapping(value = "/get-sexes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sex> getCareTypes() {
        return Arrays.asList(Sex.values());
    }

    @GetMapping(value = "/count-care-receivers/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countCareReceivers(@PathVariable("institution-name") final String institutionName) {
        log.info("Count care receivers of institute {}", institutionName);
        return careReceiverService.countCareReceiversByInstitution(institutionName);
    }

    @GetMapping(value = "/count-waiting-list/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countWaitingList(@PathVariable("institution-name") final String institutionName) {
        log.info("Get waiting list size of institute {}", institutionName);
        return careReceiverService.countWaitingListByInstitution(institutionName);
    }

    @GetMapping(value = "/count-archive/{institution-name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countArchive(@PathVariable("institution-name") final String institutionName) {
        log.info("Get archive size of institute {}", institutionName);
        return careReceiverService.countArchiveByInstitution(institutionName);
    }

    @GetMapping(value = "/count-care-receivers-date/{institution-name}/{from-date}/{to-date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> countCareReceiversByDate(@PathVariable("institution-name") final String institutionName,
                                                         @PathVariable("from-date") final String fromDateString,
                                                         @PathVariable("to-date") final String toDateString) throws ParseException {

        log.info("Get number of care receivers of institute {} from {} to {}", institutionName, fromDateString, toDateString);
        final Integer count = careReceiverService.countCareReceiversByInstitutionInTimeRange(institutionName, fromDateString, toDateString);
        return Collections.singletonMap("count", count);
    }

    @GetMapping(value = "/normative-year/{institution-name}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> normativeByYear(@PathVariable("institution-name") final String institutionName,
                                                @PathVariable("date") final String dateString) throws ParseException {
        log.info("Get yearly normative of institute {} for year {}", institutionName, dateString);
        final Integer count = careReceiverService.normativeByInstitutionAndYear(institutionName, dateString);
        return Collections.singletonMap("count", count);
    }
}
