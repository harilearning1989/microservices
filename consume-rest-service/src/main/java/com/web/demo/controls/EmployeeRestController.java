package com.web.demo.controls;

import com.web.demo.services.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emp")
public class EmployeeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);
    private final ApiService apiService;

    public EmployeeRestController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        LOGGER.info("EmployeeRestController helloWorld::::::");
        return apiService.helloWorld();
    }
}
