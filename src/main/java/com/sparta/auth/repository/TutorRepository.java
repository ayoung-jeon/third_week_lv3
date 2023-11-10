package com.sparta.auth.repository;

import com.sparta.auth.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByTutorName(String tutorName);
}
