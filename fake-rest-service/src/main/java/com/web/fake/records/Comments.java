package com.web.fake.records;

public record Comments(
        Long postId,
        Long id,
        String name,
        String email,
        String body
) {
}