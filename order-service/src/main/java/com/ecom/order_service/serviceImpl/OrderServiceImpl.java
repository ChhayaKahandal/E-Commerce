package com.ecom.order_service.serviceImpl;

import com.ecom.order_service.dto.OrderRequest;
import com.ecom.order_service.dto.OrderResponse;
import com.ecom.order_service.dto.ProductResponse;
import com.ecom.order_service.dto.UserResponse;
import com.ecom.order_service.model.Order;
import com.ecom.order_service.repository.OrderRepo;
import com.ecom.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ExternalService externalService;

    @Override
    public OrderResponse create(OrderRequest request) {

        // ✅ Circuit breaker handles external calls
        UserResponse user = externalService.getUser(request.getUserId());
        ProductResponse product =externalService.getProduct(request.getProductId());



        // Calculate total price
        Double totalPrice = product.getPrice() * request.getQuantity();

        // Build Order entity
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(totalPrice);

        // Save order
        Order savedOrder = orderRepo.save(order);

        return mapToResponse(savedOrder);
    }

    @Override
    public OrderResponse getById(Long id) {

        Order order = orderRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getAll() {

        return orderRepo.findByIsDeletedFalse()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {

        Order order = orderRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setIsDeleted(true);
        orderRepo.save(order);
    }

    // ----------------- MAPPER -----------------
    private OrderResponse mapToResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getTotalPrice()
        );
    }
}