package com.ecom.user_service.repository;

import com.ecom.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long>
{
    Optional<User> findByEmailAndIsDeletedFalse(Long id);
    List<User> findByIsDeletedfalse();
    boolean existsByEmail(String email);

    Optional<User> findByIdAndIsDeletedFalse(Long id);
}
