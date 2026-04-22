package com.ecom.product_service.service;

import com.ecom.product_service.dto.ProductRequest;
import com.ecom.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService
{
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
    ProductResponse update(Long id,ProductRequest request);
    void delete(Long id);


}
