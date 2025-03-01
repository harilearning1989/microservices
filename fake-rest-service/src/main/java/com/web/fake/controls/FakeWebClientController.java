package com.web.fake.controls;

import com.web.fake.records.*;
import com.web.fake.services.FakeWebClientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stream")
public class FakeWebClientController {

    private final FakeWebClientService fakeApiService;

    public FakeWebClientController(FakeWebClientService fakeApiService) {
        this.fakeApiService = fakeApiService;
    }

    @GetMapping(value = "/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Posts> streamAllPosts() {
        return fakeApiService.getAllPosts();
    }

    @GetMapping(value = "/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProducts() {
        return fakeApiService.fetchProducts();
    }

    @GetMapping(value = "/carts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Cart> getCarts() {
        return fakeApiService.fetchCarts();
    }

    @GetMapping(value = "/books", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> getBooks() {
        return fakeApiService.fetchBooks();
    }

    @GetMapping(value = "/authors", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Authors> getAuthors() {
        return fakeApiService.fetchAuthors();
    }

    @GetMapping(value = "/comments", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comments> getComments() {
        return fakeApiService.fetchComments();
    }

    @GetMapping(value = "/todos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Todos> getTodos() {
        return fakeApiService.fetchTodos();
    }

    @GetMapping(value = "/photos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Photos> getPhotos() {
        return fakeApiService.fetchPhotos();
    }
}

