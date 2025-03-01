package com.web.demo.services.client;

import com.web.demo.response.ApiResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class CustomExternalApiService {
    private final WebClient webClient;

    public CustomExternalApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Async("customExecutor")
    public CompletableFuture<ApiResponse> callExternalApi(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(ApiResponse::new) // Convert response to ApiResponse object
                .toFuture()
                .orTimeout(3, TimeUnit.SECONDS) // Set a max time of 3 seconds
                .exceptionally(ex -> {
                    System.err.println("Error calling API: " + url + " | Error: " + ex.getMessage());
                    return new ApiResponse("Error fetching data from " + url); // Default fallback response
                });
    }


    @Async("customExecutor")
    public <T> CompletableFuture<T> callExternalApi(String url, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType)
                .toFuture()
                .orTimeout(3, TimeUnit.SECONDS)  // Max execution time: 3 seconds
                .exceptionally(ex -> {
                    System.err.println("Error calling API: " + url + " | Error: " + ex.getMessage());
                    return null;  // Return null or a default object
                });
    }
}

