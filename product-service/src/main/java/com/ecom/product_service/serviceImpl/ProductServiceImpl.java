package com.ecom.product_service.serviceImpl;

import com.ecom.product_service.dto.ProductRequest;
import com.ecom.product_service.dto.ProductResponse;
import com.ecom.product_service.exception.ResourceNotFoundException;
import com.ecom.product_service.model.Product;
import com.ecom.product_service.repository.ProductRepo;
import com.ecom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{


    private final ProductRepo productRepo;


    //create Product
    @Override
    public ProductResponse createProduct(ProductRequest request)
    {
        Product product=new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        Product savedProduct = productRepo.save(product);

        return mapToDto(savedProduct);

    }

    //get product by id
    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
         return mapToDto(product);

    }


    //get all product
    @Override
    public List<ProductResponse> getAll()
    {
        List<Product> products = productRepo.findByIsDeletedFalse();

        return products.stream()
                .map(product -> mapToDto(product))
                .toList();
    }

    //update product details
    @Override
    public ProductResponse update(Long id, ProductRequest request)
    {
        Product product = productRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        Product savedProduct = productRepo.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public void delete(Long id)
    {
        Product product=productRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        product.setIsDeleted(true);
        productRepo.save(product);

    }


    //Mapping method : Product to ProductResponseDto
    private ProductResponse mapToDto(Product product)
    {
        ProductResponse response=
                new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        return response;
    }





}
