package com.kulkarniGroups.OrderManagementSystem.controller;

import com.kulkarniGroups.OrderManagementSystem.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;

public class InfoController {
    private final AppProperties appProperties;

    public InfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/env")
    public String environment() {
        return "Running in " + appProperties.getEnvironment()  + " environment";
    }
}
