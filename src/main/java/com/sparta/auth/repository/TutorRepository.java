package com.sparta.auth.repository;

import com.sparta.auth.entity.Tutor;
import com.sparta.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByTutorName(String tutorName);
}
