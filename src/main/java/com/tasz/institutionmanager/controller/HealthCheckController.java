package com.tasz.institutionmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HealthCheckController {
    @GetMapping(value = "/health-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, String> healthCheck() {
        log.info("Health check called!");
        return Collections.singletonMap("response", "Look ma' I'm running!");
    }
}
