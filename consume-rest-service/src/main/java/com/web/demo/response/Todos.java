package com.web.demo.response;

public record Todos(
        Long userId,
        Long id,
        String title,
        boolean completed
) {
}
