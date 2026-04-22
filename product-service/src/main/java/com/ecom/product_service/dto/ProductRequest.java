package com.ecom.product_service.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest
{
    private String name;
    private Double price;

}
