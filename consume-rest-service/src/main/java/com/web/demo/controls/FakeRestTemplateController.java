package com.web.demo.controls;

import com.web.demo.response.AllApiResponse;
import com.web.demo.services.FakeRestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake")
public class FakeRestTemplateController {

    private final FakeRestTemplateService fakeRestTemplateService;

    public FakeRestTemplateController(FakeRestTemplateService fakeRestTemplateService) {
        this.fakeRestTemplateService = fakeRestTemplateService;
    }

    @GetMapping(value = "/listThread")
    public AllApiResponse fetchAllDataThread() {
        return fakeRestTemplateService.fetchAllDataThread();
    }

    @GetMapping(value = "/listParallel")
    public AllApiResponse fetchAllData() {
        return fakeRestTemplateService.fetchAllData();
    }

    @GetMapping(value = "/list")
    public AllApiResponse getAllApisData() {
        return fakeRestTemplateService.getAllApisData();
    }

}
