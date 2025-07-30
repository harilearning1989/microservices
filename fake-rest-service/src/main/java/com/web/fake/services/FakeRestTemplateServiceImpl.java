package com.web.fake.services;

import com.web.fake.records.*;
import com.web.fake.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FakeRestTemplateServiceImpl implements FakeRestTemplateService {

    private final RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeRestTemplateServiceImpl.class);

    @Value("${fake.rest.jsonPlaceHolder}")
    private String jsonPlaceHolder;
    @Value("${fake.rest.products}")
    private String products;
    @Value("${fake.rest.carts}")
    private String carts;
    @Value("${fake.rest.fakeRestApi}")
    private String fakeRestApi;
    @Value("${sleep.time}")
    private int sleepTime;

    public FakeRestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> fetchProducts() {
        String url = products + "/products";
        List<Product> productList = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        ).getBody();
        try {
            //Thread.sleep(10000);
            LOGGER.info("fetchProducts Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime); // sleeps for 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        return CommonUtils.getLimitedList(productList,10);
    }

    @Override
    public CartResponse fetchCarts() {
        String url = carts + "/carts";
        try {
            LOGGER.info("fetchCarts Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        return restTemplate.getForObject(url, CartResponse.class);
    }

    @Override
    public List<Book> fetchBooks() {
        String url = fakeRestApi + "/api/v1/Books";
        List<Book> bookList=  restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }
        ).getBody();
        try {
            LOGGER.info("fetchBooks Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        return CommonUtils.getLimitedList(bookList,10);
    }

    @Override
    public List<Authors> fetchAuthors() {
        String url = fakeRestApi + "/api/v1/Authors";
        List<Authors> authorsList = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Authors>>() {
                }
        ).getBody();
        try {
            LOGGER.info("fetchAuthors Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        //System.out.println(10/0);
        return CommonUtils.getLimitedList(authorsList,10);
    }

    @Override
    public List<Posts> fetchPosts() {
        String url = jsonPlaceHolder + "/posts";
        List<Posts> postsList = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Posts>>() {
                }
        ).getBody();
        try {
            LOGGER.info("fetchPosts Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        //return CommonUtils.getLimitedList(postsList,10);
        return postsList;
    }

    @Override
    public List<Comments> fetchComments() {
        String url = jsonPlaceHolder + "/comments";
        List<Comments> commentsList = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comments>>() {
                }
        ).getBody();
        try {
            LOGGER.info("fetchComments Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        return CommonUtils.getLimitedList(commentsList,10);
    }

    @Override
    public List<Todos> fetchTodos() {
        String url = jsonPlaceHolder + "/todos";
        List<Todos> todosList = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todos>>() {
                }
        ).getBody();
        try {
            LOGGER.info("fetchTodos Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        return CommonUtils.getLimitedList(todosList,10);
    }

    @Override
    public List<Photos> fetchPhotos() {
        String url = jsonPlaceHolder + "/photos";
        List<Photos> photosList = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Photos>>() {
                }
        ).getBody();
        try {
            System.out.println("FakeRestTemplateServiceImpl====================fetchPhotos");
            LOGGER.info("fetchPhotos Sleeping Thread for time : {}", sleepTime);
            TimeUnit.MINUTES.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }
        return CommonUtils.getLimitedList(photosList,10);
    }
}
