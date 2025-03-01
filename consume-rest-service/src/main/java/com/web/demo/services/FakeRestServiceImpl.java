package com.web.demo.services;

import com.web.demo.response.ApiResponse;
import com.web.demo.services.client.CustomExternalApiService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class FakeRestServiceImpl implements FakeRestService {

    private final CustomExternalApiService apiService;

    public FakeRestServiceImpl(CustomExternalApiService apiService) {
        this.apiService = apiService;
    }

    public void getData(){
        String api1 = "https://jsonplaceholder.typicode.com/posts/1";
        String api2 = "https://jsonplaceholder.typicode.com/posts/2";
        String api3 = "https://jsonplaceholder.typicode.com/invalid-url"; // Invalid URL to test error handling

        CompletableFuture<ApiResponse> response1 = apiService.callExternalApi(api1);
        CompletableFuture<ApiResponse> response2 = apiService.callExternalApi(api2);
        CompletableFuture<ApiResponse> response3 = apiService.callExternalApi(api3);

        CompletableFuture.allOf(response1, response2, response3).join();

        ApiResponse apiResponse1 = response1.join();
        ApiResponse apiResponse2 = response2.join();
        ApiResponse apiResponse3 = response3.join();
    }

    public void getData1(){
        String userApi = "https://example.com/api/user";
        String orderApi = "https://example.com/api/order";
        String productApi = "https://example.com/api/product";

        CompletableFuture<UserResponse> userResponse = apiService.callExternalApi(userApi, UserResponse.class);
        CompletableFuture<OrderResponse> orderResponse = apiService.callExternalApi(orderApi, OrderResponse.class);
        CompletableFuture<ProductResponse> productResponse = apiService.callExternalApi(productApi, ProductResponse.class);

        // Wait for all APIs to complete
        CompletableFuture.allOf(userResponse, orderResponse, productResponse).join();

        // Combine responses into UnifiedResponse
        UnifiedResponse unifiedResponse = new UnifiedResponse();
        unifiedResponse.setUser(userResponse.join());
        unifiedResponse.setOrder(orderResponse.join());
        unifiedResponse.setProduct(productResponse.join());

        System.out.println("Final Combined Response: " + unifiedResponse);
    }
}
