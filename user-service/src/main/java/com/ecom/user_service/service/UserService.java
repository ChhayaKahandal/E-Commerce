package com.ecom.user_service.service;


import com.ecom.user_service.dto.UserRequest;
import com.ecom.user_service.dto.UserResponse;

import java.util.List;

public interface UserService
{
    UserResponse createUser(UserRequest request);
    UserResponse getById(Long id);
    UserResponse updateUser(Long id,UserRequest request);
    List<UserResponse> getAllUser();
    void delete(Long id);
}
