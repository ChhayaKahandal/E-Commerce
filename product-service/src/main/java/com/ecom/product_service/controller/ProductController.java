package com.ecom.product_service.controller;

import com.ecom.product_service.dto.ProductRequest;
import com.ecom.product_service.dto.ProductResponse;
import com.ecom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController
{

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request)
    {
        ProductResponse response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id)
    {
        ProductResponse response = productService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>>getAll()
    {
        List<ProductResponse> response = productService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,@RequestBody ProductRequest request)
    {
        ProductResponse response = productService.update(id,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        productService.delete(id);
        return new ResponseEntity<>("Product Deleted Succesfully",HttpStatus.OK);
    }







}
