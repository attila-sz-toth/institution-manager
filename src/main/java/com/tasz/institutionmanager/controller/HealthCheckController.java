package com.tasz.institutionmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheckController {
    @GetMapping(value = "/health-check")
    public @ResponseBody
    String healthCheck() {
        return "Look ma' I'm running!";
    }
}
