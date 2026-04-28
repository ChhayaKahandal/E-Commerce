package com.ecom.order_service.repository;

import com.ecom.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByIsDeletedFalse();

    Optional<Order> findByIdAndIsDeletedFalse(Long id);
}