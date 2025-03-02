package com.web.demo.response;

public record Photos(
        Long albumId,
        Long id,
        String title,
        String url,
        String thumbnailUrl
) {
}
