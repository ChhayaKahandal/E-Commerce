package com.ecom.order_service.client;


import com.ecom.order_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//This tells Spring Cloud that this interface is a Feign client
//It will call the service registered in Eureka with name "PRODUCT-SERVICE"
@FeignClient(name="PRODUCT-SERVICE")
public interface ProductClient
{

    //This maps to the API endpoint in Product Service.When you call getProduct(), it internally calls:
    //http://PRODUCT-SERVICE/product/{id}
    @GetMapping("/product/{id}")
    ProductResponse getProduct(@PathVariable Long id);


}
