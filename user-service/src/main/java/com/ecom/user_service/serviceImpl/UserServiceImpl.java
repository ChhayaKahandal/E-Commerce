package com.ecom.user_service.serviceImpl;

import com.ecom.user_service.dto.UserRequest;
import com.ecom.user_service.dto.UserResponse;
import com.ecom.user_service.model.User;
import com.ecom.user_service.repository.UserRepo;
import com.ecom.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepo userRepo;
    @Override
    public UserResponse createUser(UserRequest request)
    {
          if(userRepo.existsByEmail(request.getEmail()))
          {
              throw new RuntimeException("Emai aldready exists");
          }
          User user=new User();
          user.setName(request.getName());
          user.setEmail(request.getEmail());
          user.setPassword(request.getPassword());
          User savedUser=userRepo.save(user);
          return mapToDto(savedUser);
    }

    @Override
    public UserResponse getById(Long id)
    {
        User user=userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new RuntimeException("User not found"));
        return mapToDto(user);

    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user=userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        User savedUser = userRepo.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public List<UserResponse> getAllUser()
    {
        List<User> users = userRepo.findByIsDeletedFalse();
        return users.stream()
                .map(product -> mapToDto(product))
                .toList();
    }

    @Override
    public void delete(Long id)
    {

       User user=userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new RuntimeException("User not found"));
       user.setIsDeleted(true);
       userRepo.save(user);
    }

    //Mapping method : Product to ProductResponseDto
    private UserResponse mapToDto(User user)
    {
        UserResponse response=
                new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        return response;
    }


}
