package com.web.fake.utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CommonUtils {

    public static <T> List<T> getLimitedList(List<T> inputList, int limit) {
        return Optional.ofNullable(inputList)
                .orElseGet(Collections::emptyList)
                .stream()
                .limit(limit)
                .toList();
    }
}
