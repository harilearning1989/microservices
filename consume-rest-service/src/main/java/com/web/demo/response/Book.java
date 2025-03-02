package com.web.demo.response;

public record Book(
        Long id,
        String title,
        String description,
        String excerpt,
        String publishDate
) {}

