package com.sparta.auth.service;

import com.sparta.auth.dto.LectureRequestDto;
import com.sparta.auth.dto.LectureResponseDto;
import com.sparta.auth.entity.Lecture;
import com.sparta.auth.entity.LectureEnum;
import com.sparta.auth.entity.Tutor;
import com.sparta.auth.repository.LectureRepository;
import com.sparta.auth.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.EnumSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public LectureResponseDto registerLecture(LectureRequestDto requestDto) {
        // 로그 남기기
        log.info("Registering a new lecture with title: {}", requestDto.getTitle());

        // 중복 강의 체크
        if (lectureRepository.existsByTitle(requestDto.getTitle())) {
            // 중복 강의가 있는 경우 로그를 남기고 예외를 발생.
            log.warn("Lecture registration failed: Duplicate lecture title {}", requestDto.getTitle());
            throw new IllegalStateException("Lecture with title " + requestDto.getTitle() + " already exists");
        }

        // Tutor 엔티티 조회
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tutor ID: " + requestDto.getTutorId()));

        // category 유효성 검사
        // Enum 값 검증
        if (!EnumSet.allOf(LectureEnum.class).contains(requestDto.getCategory())) {
            throw new IllegalArgumentException("Invalid category: " + requestDto.getCategory());
        }

        // Lecture 엔티티 생성
        Lecture lecture = new Lecture(requestDto, tutor);
        Lecture savedLecture;

        // 저장 시도
        try {
            savedLecture = lectureRepository.save(lecture);
        } catch (DataAccessException e) {
            // 데이터베이스 액세스 중 예외 발생 시 로그를 발생
            log.error("Lecture registration failed: Database access error", e);
            throw e; // 예외를 상위 계층으로 전파
        }

        // 저장 성공 로그
        log.info("Lecture registered successfully with id: {}", savedLecture.getLectureId());

        // LectureResponseDto 생성 및 반환
        return new LectureResponseDto(savedLecture);
    }

    // 선택한 강사 조회
    public LectureResponseDto getLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의는 없습니다."));
        return new LectureResponseDto(lecture);
    }

    // 강사 정보 수정
    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto) {
        // 기존 Tutor 조회
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강사는 없습니다."));

        // 수정 사항 반영
        lecture.setTitle(requestDto.getTitle());
        lecture.setPrice(requestDto.getPrice());
        lecture.setDescription(requestDto.getDescription());
        lecture.setCategory(requestDto.getCategory());

        // 업데이트된 Tutor 저장
        Lecture updatedLecture = lectureRepository.save(lecture);

        // 업데이트된 정보로 ResponseDto 생성
        return new LectureResponseDto(updatedLecture);
    }


}
