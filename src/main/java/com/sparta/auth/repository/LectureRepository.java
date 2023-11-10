package com.sparta.auth.repository;

import com.sparta.auth.entity.Lecture;
import com.sparta.auth.entity.LectureEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    boolean existsByTitle(String title);

    List<Lecture> findByCategoryOrderByCreatedAtDesc(LectureEnum category);

//    List<Lecture> findByTutorIdOrderByCreatedAtDesc(Long tutorId);

//    // 또는 JPQL을 사용
//    @Query("SELECT l FROM Lecture l WHERE l.tutor.id = :tutorId ORDER BY l.createdAt DESC")
//    List<Lecture> findLecturesByTutorId(@Param("tutorId") Long tutorId);
}
