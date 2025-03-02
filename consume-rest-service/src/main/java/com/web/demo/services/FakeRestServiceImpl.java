package com.web.demo.services;

import com.web.demo.response.*;
import com.web.demo.services.client.CustomExternalApiService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FakeRestServiceImpl implements FakeRestService {

    private final CustomExternalApiService apiService;

    public FakeRestServiceImpl(CustomExternalApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Flux<FakeResponse> getAllFakeApis() {
        CompletableFuture<List<Posts>> postsFuture = fetchWithLogging(apiService.getAllPosts(), "Posts");
        CompletableFuture<List<Product>> productsFuture = fetchWithLogging(apiService.getAllProducts(), "Products");
        CompletableFuture<List<Book>> booksFuture = fetchWithLogging(apiService.getAllBooks(), "Books");

        Mono<List<Posts>> postsMono = Mono.fromFuture(postsFuture)
                .doOnNext(posts -> System.out.println("Fetched Posts Count: " + posts.size()))
                .map(posts -> posts.stream().limit(10).toList())
                .map(this::updatePosts);

        Mono<List<Product>> productsMono = Mono.fromFuture(productsFuture)
                .doOnNext(products -> System.out.println("Fetched Products Count: " + products.size()))
                .map(products -> products.stream().limit(10).toList())
                .map(this::updateProducts);

        Mono<List<Book>> booksMono = Mono.fromFuture(booksFuture)
                .doOnNext(products -> System.out.println("Fetched Books Count: " + products.size()))
                .map(books -> books.stream().limit(10).toList());

        return Mono.zip(postsMono, productsMono, booksMono)
                .map(tuple -> {
                    List<Posts> updatedPosts = tuple.getT1();
                    List<Product> updatedProducts = tuple.getT2();
                    List<Book> books = tuple.getT3();
                    // Create FakeResponse with both lists
                    return new FakeResponse(updatedPosts, updatedProducts, books, null, null, null, null);
                })
                .flux();
    }

    private List<Posts> updatePosts(List<Posts> posts) {
        return posts.stream()
                .map(post -> new Posts(post.userId(), post.id(), post.title() + " - Updated", post.body()))
                .toList();
    }

    private List<Product> updateProducts(List<Product> products) {
        return products.stream()
                .map(product -> new Product(product.id(), product.title() + " - Updated", product.description(), product.price(), product.category(), product.image()))
                .toList();
    }

    public Flux<FakeResponse> getAllFakeApisTmp() {
        //CompletableFuture<List<Posts>> cartsFuture = apiService.getAllCarts();
        CompletableFuture<List<Posts>> postsFuture = fetchWithLogging(apiService.getAllPosts(), "Posts");
        CompletableFuture<List<Product>> productsFuture = fetchWithLogging(apiService.getAllProducts(), "Products");
        //CompletableFuture<List<Book>> booksFuture = fetchWithLogging(apiService.getAllBooks(), "Books");
        //CompletableFuture<List<Authors>> authorsFuture = fetchWithLogging(apiService.getAllAuthors(), "Authors");
        //CompletableFuture<List<Comments>> commentsFuture = fetchWithLogging(apiService.getAllComments(), "Comments");
        //CompletableFuture<List<Todos>> todosFuture = fetchWithLogging(apiService.getAllTodos(), "Todos");
        //CompletableFuture<List<Photos>> photosFuture = fetchWithLogging(apiService.getAllPhotos(), "Photos");

        /*CompletableFuture<List<Posts>> postsFuture = apiService.getAllPosts()
                .exceptionally(ex -> {
                    System.err.println("Error fetching posts: " + ex.getMessage());
                    return List.of(); // Return empty list in case of error
                });*/

        /*return CompletableFuture.allOf(postsFuture, productsFuture, booksFuture, authorsFuture, commentsFuture, todosFuture, photosFuture)
                .thenApply(voidResult -> new FakeResponse(
                        postsFuture.join(),
                        productsFuture.join(),
                        booksFuture.join(),
                        authorsFuture.join(),
                        commentsFuture.join(),
                        todosFuture.join(),
                        photosFuture.join()
                ))
                .thenApply(Flux::just)
                .exceptionally(ex -> {
                    System.err.println("Error fetching data: " + ex.getMessage());
                    return Flux.empty();
                })
                .join(); */

        return CompletableFuture.allOf(postsFuture)
                .thenApply(voidResult -> new FakeResponse(
                        postsFuture.join(),
                        productsFuture.join(), null, null, null, null, null
                ))
                .thenApply(Flux::just)  // Convert CompletableFuture<FakeResponse> to Flux<FakeResponse>
                .exceptionally(ex -> {
                    System.err.println("Error fetching data: " + ex.getMessage());
                    return Flux.empty(); // Return empty Flux in case of an error
                })
                .join(); // Block until all futures are complete
    }

    // Generic helper method to log errors
    private <T> CompletableFuture<List<T>> fetchWithLogging(CompletableFuture<List<T>> future, String apiName) {
        return future.exceptionally(ex -> {
            System.err.println("Error in API: " + apiName + " - " + ex.getMessage());
            return List.of(); // Return empty list on error
        });
    }

    public void getData() {
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

    /*public void getData1(){
        String userApi = "https://example.com/api/user";
        String orderApi = "https://example.com/api/order";
        String productApi = "https://example.com/api/product";

        CompletableFuture<UserResponse> userResponse = apiService.callExternalApi(userApi, UserResponse.class);
        CompletableFuture<OrderResponse> orderResponse = apiService.callExternalApi(orderApi, OrderResponse.class);
        CompletableFuture<ProductResponse> productResponse = apiService.callExternalApi(productApi, ProductResponse.class);

        CompletableFuture.allOf(userResponse, orderResponse, productResponse).join();

        UnifiedResponse unifiedResponse = new UnifiedResponse();
        unifiedResponse.setUser(userResponse.join());
        unifiedResponse.setOrder(orderResponse.join());
        unifiedResponse.setProduct(productResponse.join());

        System.out.println("Final Combined Response: " + unifiedResponse);
    }*/
}
