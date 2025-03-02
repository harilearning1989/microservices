package com.web.demo.services;

import com.web.demo.response.FakeResponse;
import reactor.core.publisher.Flux;

public interface FakeRestService {
    Flux<FakeResponse> getAllFakeApis();
}
