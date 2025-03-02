package com.web.demo.services.client;

import com.web.demo.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class CustomExternalApiService {
    private final WebClient webClient;

    public CustomExternalApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${fake.rest.fakeApiUrl}")
    private String fakeApiUrl;

    public CompletableFuture<List<Posts>> getAllPosts() {
        return webClient.get()
                .uri(fakeApiUrl+"posts")
                .retrieve()
                .bodyToFlux(Posts.class)
                //.timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
    }

    public CompletableFuture<List<Product>> getAllProducts() {
        return webClient.get()
                .uri(fakeApiUrl+"products")
                .retrieve()
                .bodyToFlux(Product.class)
                //.timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
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

    //ignore for now
    public CompletableFuture<List<Posts>> getAllCarts() {
        return webClient.get()
                .uri(fakeApiUrl+"posts")
                .retrieve()
                .bodyToFlux(Posts.class)
                .timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
    }

    public CompletableFuture<List<Book>> getAllBooks() {
        return webClient.get()
                .uri(fakeApiUrl+"books")
                .retrieve()
                .bodyToFlux(Book.class)
                .timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
    }

    public CompletableFuture<List<Authors>> getAllAuthors() {
        return webClient.get()
                .uri(fakeApiUrl+"authors")
                .retrieve()
                .bodyToFlux(Authors.class)
                .timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();  // Convert Mono<List<Authors>> to CompletableFuture<List<Authors>>
    }

    public CompletableFuture<List<Comments>> getAllComments() {
        return webClient.get()
                .uri(fakeApiUrl+"comments")
                .retrieve()
                .bodyToFlux(Comments.class)
                .timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
    }

    public CompletableFuture<List<Todos>> getAllTodos() {
        return webClient.get()
                .uri(fakeApiUrl+"todos")
                .retrieve()
                .bodyToFlux(Todos.class)
                .timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
    }

    public CompletableFuture<List<Photos>> getAllPhotos() {
        return webClient.get()
                .uri(fakeApiUrl+"photos")
                .retrieve()
                .bodyToFlux(Photos.class)
                .timeout(Duration.ofSeconds(5))  // ⏳ Set timeout of 5 seconds
                .collectList()  // Convert Flux<Authors> to Mono<List<Authors>>
                .onErrorResume(this::handleError)  // Handle errors
                .toFuture();
    }

    private <T> Mono<List<T>> handleError(Throwable ex) {
        if (ex instanceof WebClientResponseException) {
            System.err.println("HTTP Error: " + ((WebClientResponseException) ex).getStatusCode());
        } else if (ex instanceof TimeoutException) {
            System.err.println("Request timed out!");
        } else {
            System.err.println("Unexpected error: " + ex.getMessage());
        }
        return Mono.just(List.of()); // Return empty list in case of an error
    }

}

