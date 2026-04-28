package com.ecom.order_service.service;

import com.ecom.order_service.dto.OrderRequest;
import com.ecom.order_service.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse getById(Long id);

    List<OrderResponse> getAll();

    void delete(Long id);
}