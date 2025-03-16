package com.web.demo.utils;

import com.web.demo.response.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private String jwtToken;
    String secretKey = "AvHGRK8C0ia4uOuxxqPD5DTbWC9F9TWvPStp3pb7ARo0oK2mJ3pd3YG4lxA9i8bj6OTbadweheufHNyG";

    public String getToken() {
        LOGGER.info("JwtTokenService getToken ::::");
        if (jwtToken == null || isTokenExpired(jwtToken)) {
            jwtToken = fetchJwtToken();
        }
        return jwtToken;
    }

    private String fetchJwtToken() {
        LOGGER.info("JwtTokenService fetchJwtToken ::::");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String API_USERNAME = "rohan";
        String API_PASSWORD = "12345";
        Map<String, String> requestBody = Map.of(
                "username", API_USERNAME,
                "password", API_PASSWORD
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        String AUTH_URL = "http://localhost:8081/auth/login";
        ResponseEntity<JwtResponse> response = restTemplate.exchange(AUTH_URL, HttpMethod.POST, request, JwtResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().token();
        }
        return null; // Handle failure case
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
