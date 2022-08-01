package com.example.hospital2022.repository;

import com.example.hospital2022.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByLogin(String login);
}