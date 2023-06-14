package com.example.transportcompany.repositories;


import com.example.transportcompany.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByPhoneNumber(String userPhone);
}