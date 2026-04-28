package com.ecom.user_service.controller;

import com.ecom.user_service.dto.UserRequest;
import com.ecom.user_service.dto.UserResponse;
import com.ecom.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request)
    {
        UserResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id)
    {
        UserResponse response = userService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>>getAll()
    {
        List<UserResponse> response = userService.getAllUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,@RequestBody UserRequest request)
    {
        UserResponse response = userService.updateUser(id,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        userService.delete(id);
        return new ResponseEntity<>("Product Deleted Succesfully",HttpStatus.OK);
    }




}
