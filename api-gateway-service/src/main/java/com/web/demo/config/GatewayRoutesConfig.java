package com.web.demo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Value("${FAKE_SERVICE_URI:http://localhost:8082}")
    private String fakeServiceUri;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("fake-rest-service", r -> r
                        .path("/fake/**")
                        .uri(fakeServiceUri)
                )
                .build();
    }

    @PostConstruct
    public void logConfig() {
        System.out.println("Using FAKE_SERVICE_URI = " + fakeServiceUri);
    }
}

