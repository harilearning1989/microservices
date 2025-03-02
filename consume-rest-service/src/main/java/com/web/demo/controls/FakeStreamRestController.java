package com.web.demo.controls;

import com.web.demo.response.FakeResponse;
import com.web.demo.services.FakeRestService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("fake")
public class FakeStreamRestController {

    private final FakeRestService fakeRestService;

    public FakeStreamRestController(FakeRestService fakeRestService) {
        this.fakeRestService = fakeRestService;
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FakeResponse> streamAllPosts() {
        return fakeRestService.getAllFakeApis();
    }


}
