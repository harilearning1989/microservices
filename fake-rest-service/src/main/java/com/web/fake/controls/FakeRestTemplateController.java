package com.web.fake.controls;

import com.web.fake.records.*;
import com.web.fake.services.FakeRestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fake")
public class FakeRestTemplateController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final FakeRestTemplateService fakeRestTemplateService;

    public FakeRestTemplateController(FakeRestTemplateService fakeRestTemplateService) {
        this.fakeRestTemplateService = fakeRestTemplateService;
    }

    @GetMapping(value = "/photos")
    public List<Photos> getPhotos() {
        LOGGER.info("Entry path: {} ", this.getClass().getName());
        return fakeRestTemplateService.fetchPhotos();
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return fakeRestTemplateService.fetchProducts();
    }

    @GetMapping("/carts")
    public CartResponse getCarts() {
        return fakeRestTemplateService.fetchCarts();
    }

    @GetMapping(value = "/posts")
    public List<Posts> streamAllPosts() {
        return fakeRestTemplateService.fetchPosts();
    }

    @GetMapping("/api/v1/Books")
    public List<Book> getBooks() {
        return fakeRestTemplateService.fetchBooks();
    }

    @GetMapping(value = "/api/v1/Authors")
    public List<Authors> getAuthors() {
        return fakeRestTemplateService.fetchAuthors();
    }

    @GetMapping("/comments")
    public List<Comments> getComments() {
        return fakeRestTemplateService.fetchComments();
    }

    @GetMapping("/todos")
    public List<Todos> getTodos() {
        return fakeRestTemplateService.fetchTodos();
    }


}
