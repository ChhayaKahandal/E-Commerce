package com.ecom.user_service.repository;

import com.ecom.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long>
{
    boolean existsByEmai(String email);

}
