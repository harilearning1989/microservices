package com.web.fake.config.rc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestHeadersSpec;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;
import org.springframework.web.client.support.RestClientAdapter;


public class HttpClientFactory {

    /*public static <T> T createClient(String baseUrl, Map<String, String> headers, Class<T> clientClass) {
        RestClient.Builder builder = RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory())
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    headers.forEach(httpHeaders::add);
                });

        RestClient restClient = builder.build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(RestClientAdapter.forClient(restClient))
                .build();

        return factory.createClient(clientClass);
    }*/
}

