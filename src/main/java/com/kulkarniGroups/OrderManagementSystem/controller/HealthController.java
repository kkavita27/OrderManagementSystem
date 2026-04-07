package com.kulkarniGroups.OrderManagementSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String HelloWorld()
    {  //Simple end point
        return "Hello Kavita!";
    }

    @GetMapping("/health")
    public String Health()
    {
        return "this is health api ";
    }
}
