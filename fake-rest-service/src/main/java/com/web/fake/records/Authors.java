package com.web.fake.records;

public record Authors(
        Long id,
        Long idBook,
        String firstName,
        String lastName
) {
}
