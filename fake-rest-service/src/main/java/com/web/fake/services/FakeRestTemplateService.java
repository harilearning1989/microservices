package com.web.fake.services;

import com.web.fake.records.*;

import java.util.List;

public interface FakeRestTemplateService {
    List<Posts> fetchPosts();

    List<Product> fetchProducts();

    CartResponse fetchCarts();

    List<Book> fetchBooks();

    List<Authors> fetchAuthors();

    List<Comments> fetchComments();

    List<Todos> fetchTodos();

    List<Photos> fetchPhotos();

}
