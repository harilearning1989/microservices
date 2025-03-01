package com.web.fake.records;

public record Todos(
        Long userId,
        Long id,
        String title,
        boolean completed
) {
}
