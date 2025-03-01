package com.web.fake.records;

public record Photos(
        Long albumId,
        Long id,
        String title,
        String url,
        String thumbnailUrl
) {
}
