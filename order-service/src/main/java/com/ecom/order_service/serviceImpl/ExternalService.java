package com.ecom.order_service.serviceImpl;

import com.ecom.order_service.client.ProductClient;
import com.ecom.order_service.client.UserClient;
import com.ecom.order_service.dto.ProductResponse;
import com.ecom.order_service.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final UserClient userClient;
    private final ProductClient productClient;

    // ✅ USER CACHE
    @Cacheable(value = "users", key = "#userId")
    @CircuitBreaker(name = "USER_SERVICE", fallbackMethod = "userFallback")
    public UserResponse getUser(Long userId) {
        System.out.println("Calling USER SERVICE");
        return userClient.getUser(userId);
    }

    public UserResponse userFallback(Long userId, Throwable e) {
        UserResponse user = new UserResponse();
        user.setId(userId);
        user.setName("Default User");
        return user;
    }

    // ✅ PRODUCT CACHE
    @Cacheable(value = "products", key = "#productId")
    @CircuitBreaker(name = "PRODUCT_SERVICE", fallbackMethod = "productFallback")
    public ProductResponse getProduct(Long productId) {
        System.out.println("Calling PRODUCT SERVICE");
        return productClient.getProduct(productId);
    }

    public ProductResponse productFallback(Long productId, Throwable e) {
        ProductResponse product = new ProductResponse();
        product.setId(productId);
        product.setPrice(0.0);
        return product;
    }
}