package com.ecom.product_service.repository;

import com.ecom.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>
{

    Optional<Product> findByIdAndIsDeletedFalse(Long id);

    List<Product> findByIsDeletedFalse();
}
