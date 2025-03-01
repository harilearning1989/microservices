package com.web.fake.services;

import com.web.fake.records.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class FakeWebClientServiceImpl implements FakeWebClientService {

    /*private final WebClient webClient;

    public FakeApiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }*/

    @Value("${fake.rest.jsonPlaceHolder}")
    private String jsonPlaceHolder;
    @Value("${fake.rest.products}")
    private String products;
    @Value("${fake.rest.carts}")
    private String carts;
    @Value("${fake.rest.fakeRestApi}")
    private String fakeRestApi;

    private final WebClient.Builder webClientBuilder;

    public FakeWebClientServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    private WebClient createClient(String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl).build();
    }

    /*@Override
    public Flux<FakeApiResponse> getAllPosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(FakeApiResponse.class);
    }*/

    @Override
    public Flux<Posts> getAllPosts() {
        return createClient(jsonPlaceHolder)
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Posts.class);
    }

    public Flux<Product> fetchProducts() {
        return createClient(products)
                .get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class); // Convert JSON response to Flux<Product>
    }

    public Flux<Cart> fetchCarts() {
        return createClient(carts)
                .get()
                .uri("/carts")
                .retrieve()
                .bodyToFlux(Cart.class); // Convert JSON response to Flux<Product>
    }

    public Flux<Book> fetchBooks() {
        return createClient(fakeRestApi)
                .get().uri("/api/v1/Books")
                .retrieve()
                .bodyToFlux(Book.class); // Convert JSON response to Flux<Product>
    }

    public Flux<Authors> fetchAuthors() {
        return createClient(fakeRestApi)
                .get().uri("/api/v1/Authors")
                .retrieve()
                .bodyToFlux(Authors.class);
    }

    public Flux<Comments> fetchComments() {
        return createClient(jsonPlaceHolder)
                .get().uri("/comments")
                .retrieve()
                .bodyToFlux(Comments.class);
    }

    public Flux<Todos> fetchTodos() {
        return createClient(jsonPlaceHolder)
                .get().uri("/todos")
                .retrieve()
                .bodyToFlux(Todos.class);
    }

    public Flux<Photos> fetchPhotos() {
        return createClient(jsonPlaceHolder)
                .get().uri("/photos")
                .retrieve()
                .bodyToFlux(Photos.class);
    }
}
