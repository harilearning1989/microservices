package com.web.demo.response;

import java.util.List;

public record FakeResponse(
        List<Posts> posts,
        List<Product> products,
        List<Book> books,
        List<Authors> authors,
        List<Comments> comments,
        List<Todos> todos,
        List<Photos> photos
) { }

