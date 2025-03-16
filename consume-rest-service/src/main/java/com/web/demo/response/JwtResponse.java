package com.web.demo.response;

import java.util.List;

public record JwtResponse(String token, String username, List<String> roles, int statusCode) {}

