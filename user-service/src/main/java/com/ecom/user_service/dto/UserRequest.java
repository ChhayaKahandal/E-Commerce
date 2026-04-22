package com.ecom.user_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest
{
    @NotBlank(message="Name is required")
    private String name;

    @Email(message="Invalid email format")
    private String email;

    @NotBlank(message="Password is required")
    private String password;
}
