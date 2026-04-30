package com.ecom.order_service.serviceImpl;

import com.ecom.order_service.client.ProductClient;
import com.ecom.order_service.client.UserClient;
import com.ecom.order_service.dto.ProductResponse;
import com.ecom.order_service.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class CircuitBreakerImpl
{



        private final UserClient userClient;
        private final ProductClient productClient;

        @CircuitBreaker(name = "USER_SERVICE", fallbackMethod = "userFallback")
        public UserResponse getUser(Long userId) {
            return userClient.getUser(userId);
        }

        public UserResponse userFallback(Long userId, Throwable e) {
            throw new RuntimeException("User service unavailable");
        }

        @CircuitBreaker(name = "PRODUCT_SERVICE", fallbackMethod = "productFallback")
        public ProductResponse getProduct(Long productId) {
            return productClient.getProduct(productId);
        }

        public ProductResponse productFallback(Long productId, Throwable e) {
            throw new RuntimeException("Product service unavailable");
        }
    }

