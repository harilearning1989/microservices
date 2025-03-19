package com.web.demo.services;

import com.web.demo.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeRestTemplateServiceImpl implements FakeRestTemplateService {

    private final RestTemplate restTemplate;

    @Value("${fake.rest.jsonPlaceHolder}")
    private String jsonPlaceHolder;

    public FakeRestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AllApiResponse getAllApisData() {

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
}
