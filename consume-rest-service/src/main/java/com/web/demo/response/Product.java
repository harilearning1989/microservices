package com.web.demo.response;

import java.math.BigDecimal;

public record Product(
        Long id,
        String title,
        String description,
        BigDecimal price,
        String category,
        String image
) {}

