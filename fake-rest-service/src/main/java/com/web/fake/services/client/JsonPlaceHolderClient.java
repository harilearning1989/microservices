package com.web.fake.services.client;

import com.web.fake.records.Posts;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/posts")
public interface JsonPlaceHolderClient {
    @GetExchange("/{id}")
    Posts getPostById(int id);
}

