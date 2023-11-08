package com.sparta.auth.repository;

import com.sparta.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 회원 중복 확인을 위해 -> findByUsername
    Optional<User> findByUsername(String username);
    // email 중복확인
    Optional<User> findByEmail(String email);
}
