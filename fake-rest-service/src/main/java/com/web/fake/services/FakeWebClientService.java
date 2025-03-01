package com.web.fake.services;

import com.web.fake.records.*;
import reactor.core.publisher.Flux;

public interface FakeWebClientService {

    Flux<Posts> getAllPosts();
    Flux<Product> fetchProducts();
    Flux<Cart> fetchCarts();
    Flux<Book> fetchBooks();
    Flux<Authors> fetchAuthors();
    Flux<Comments> fetchComments();
    Flux<Todos> fetchTodos();
    Flux<Photos> fetchPhotos();
}
