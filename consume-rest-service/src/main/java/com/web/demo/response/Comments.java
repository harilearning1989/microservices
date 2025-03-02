package com.web.demo.response;

public record Comments(
        Long postId,
        Long id,
        String name,
        String email,
        String body
) {
}