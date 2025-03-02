package com.web.demo.response;

public record Authors(
        Long id,
        Long idBook,
        String firstName,
        String lastName
) {
}
