package com.web.demo.services;

import com.web.demo.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class FakeRestTemplateServiceImpl implements FakeRestTemplateService {

    private final RestTemplate restTemplate;

    @Value("${fake.rest.jsonPlaceHolder}")
    private String jsonPlaceHolder;

    private final ExecutorService executorService = Executors.newFixedThreadPool(8); // Parallel execution

    public FakeRestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AllApiResponse getAllApisData() {
        Instant start = Instant.now(); // Start time measurement

        AllApiResponse allApiResponse = getAPIsData();

        Instant end = Instant.now(); // End time measurement
        double durationInSeconds = Duration.between(start, end).toNanos() / 1_000_000_000.0; // Convert nanoseconds to seconds
        System.out.println("Execution Time: " + durationInSeconds + " seconds");

        return allApiResponse;
    }

    private AllApiResponse getAPIsData() {
        AllApiResponse allApiResponse = new AllApiResponse();

        List<Product> productList = restTemplate.exchange(
                jsonPlaceHolder + "products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        ).getBody();

        allApiResponse.setProductList(productList);

        List<Book> bookList = restTemplate.exchange(
                jsonPlaceHolder + "books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }
        ).getBody();

        allApiResponse.setBookList(bookList);

        List<Authors> authorsList = restTemplate.exchange(
                jsonPlaceHolder + "authors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Authors>>() {
                }
        ).getBody();

        allApiResponse.setAuthorsList(authorsList);

        List<Posts> postsList = restTemplate.exchange(
                jsonPlaceHolder + "posts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Posts>>() {
                }
        ).getBody();

        allApiResponse.setPostsList(postsList);

        List<Comments> commentsList = restTemplate.exchange(
                jsonPlaceHolder + "comments",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comments>>() {
                }
        ).getBody();

        allApiResponse.setCommentsList(commentsList);

        List<Todos> todosList = restTemplate.exchange(
                jsonPlaceHolder + "todos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todos>>() {
                }
        ).getBody();

        allApiResponse.setTodosList(todosList);

        List<Photos> photosList = restTemplate.exchange(
                jsonPlaceHolder + "photos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Photos>>() {
                }
        ).getBody();

        allApiResponse.setPhotosList(photosList);

        return allApiResponse;
    }

    @Override
    public AllApiResponse fetchAllData() {
        Instant start = Instant.now();
        AllApiResponse allApiResponse = new AllApiResponse();
        CompletableFuture<List<Product>> productsFuture =
                fetchAsync("products", new ParameterizedTypeReference<>() {});
        CompletableFuture<List<Book>> booksFuture =
                fetchAsync("books", new ParameterizedTypeReference<>() {});
        CompletableFuture<List<Authors>> authorsFuture =
                fetchAsync("authors", new ParameterizedTypeReference<>() {});
        CompletableFuture<List<Posts>> postsFuture =
                fetchAsync("posts", new ParameterizedTypeReference<>() {});
        CompletableFuture<List<Comments>> commentsFuture =
                fetchAsync("comments", new ParameterizedTypeReference<>() {});
        CompletableFuture<List<Todos>> todosFuture =
                fetchAsync("todos", new ParameterizedTypeReference<>() {});
        CompletableFuture<List<Photos>> photosFuture =
                fetchAsync("photos", new ParameterizedTypeReference<>() {});

        CompletableFuture.allOf(productsFuture, booksFuture, authorsFuture, postsFuture,
                commentsFuture, todosFuture, photosFuture).join();

        allApiResponse.setProductList(productsFuture.join());
        allApiResponse.setBookList(booksFuture.join());
        allApiResponse.setAuthorsList(authorsFuture.join());
        allApiResponse.setPostsList(postsFuture.join());
        allApiResponse.setCommentsList(commentsFuture.join());
        allApiResponse.setTodosList(todosFuture.join());
        allApiResponse.setPhotosList(photosFuture.join());

        Instant end = Instant.now(); // End time measurement
        double durationInSeconds = Duration.between(start, end).toNanos() / 1_000_000_000.0; // Convert nanoseconds to seconds
        System.out.println("Execution Time: " + durationInSeconds + " seconds");

        return allApiResponse;
    }

    private <T> CompletableFuture<List<T>> fetchAsync(String endpoint, ParameterizedTypeReference<List<T>> typeRef) {
        return CompletableFuture.supplyAsync(() -> {
                    try {
                        return restTemplate.exchange(
                                jsonPlaceHolder + endpoint,
                                HttpMethod.GET,
                                null,
                                typeRef
                        ).getBody();
                    } catch (Exception e) {
                        System.err.println("Error fetching " + endpoint + ": " + e.getMessage());
                        return null; // 🛑 Return null instead of an empty list (ignored later)
                    }
                }, executorService).orTimeout(1, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    System.err.println("Timeout for " + endpoint);
                    return null; // 🛑 Ignore this API response
                });
    }
    /*private <T> CompletableFuture<List<T>> fetchAsync(String endpoint, ParameterizedTypeReference<List<T>> typeRef) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return restTemplate.exchange(
                        jsonPlaceHolder + endpoint,
                        HttpMethod.GET,
                        null,
                        typeRef
                ).getBody();
            } catch (Exception e) {
                System.err.println("Failed to fetch " + endpoint + ": " + e.getMessage());
                return Collections.emptyList();
            }
        });
    }*/
}
