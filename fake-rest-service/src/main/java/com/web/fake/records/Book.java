package com.web.fake.records;

public record Book(
        Long id,
        String title,
        String description,
        String excerpt,
        String publishDate
) {}

