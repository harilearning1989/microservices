package com.web.fake.config.rc;

import com.web.fake.services.client.JsonPlaceHolderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class JsonPlaceholderClientConfig {

   /* @Bean
    public JsonPlaceHolderClient jsonPlaceHolderClient() {
        Map<String, String> headers = Map.of(
                "Authorization", "Bearer your-token",
                "Custom-Header", "CustomValue"
        );

        return HttpClientFactory.createClient(
                "https://jsonplaceholder.typicode.com",
                headers,
                JsonPlaceHolderClient.class
        );
    }*/
}

