package com.ecom.order_service.client;

import com.ecom.order_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductClient
{
    @GetMapping("/product/{id}")
    ProductResponse getProduct(@PathVariable Long id);


}
