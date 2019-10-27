package com.tasz.institutionmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class HealthCheckController {
    @GetMapping(value = "/health-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> healthCheck() {
        log.info("Health check called!");
        return Collections.singletonMap("response", "Look ma' I'm running!");
    }

    @GetMapping(value = "/protected-health-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> protectedHealthCheck() {
        log.info("Health check called on a protected endpoint!");
        return Collections.singletonMap("response", "Look ma' I'm running!");
    }
}
