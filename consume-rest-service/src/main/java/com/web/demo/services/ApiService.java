package com.web.demo.services;

import com.web.demo.utils.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtTokenService jwtTokenService;

    @Value("${emp.service.url}")
    private String employeeUrl;

    public ApiService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    public ResponseEntity<String> helloWorld() {
        LOGGER.info("ApiService helloWorld::::");
        HttpHeaders headers = new HttpHeaders();
        String token = jwtTokenService.getToken();
        LOGGER.info("ApiService helloWorld token::::{}",token);
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(employeeUrl, HttpMethod.GET, entity, String.class);
    }
}
