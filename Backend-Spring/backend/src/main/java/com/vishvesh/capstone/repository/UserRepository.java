package com.vishvesh.capstone.repository;

import com.vishvesh.capstone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
