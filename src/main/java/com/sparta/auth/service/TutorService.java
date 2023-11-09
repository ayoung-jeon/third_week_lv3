package com.sparta.auth.service;


import com.sparta.auth.dto.TutorRequestDto;
import com.sparta.auth.dto.TutorResponseDto;
import com.sparta.auth.entity.Tutor;
import com.sparta.auth.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorService {

    private  final TutorRepository tutorRepository;

    public TutorResponseDto registerTutor(TutorRequestDto requestDto) {
        // 로그 남기기
        log.info("Registering a new tutor with name: {}", requestDto.getTutorName());

        // 중복 이름 체크
        if (tutorRepository.existsByTutorName(requestDto.getTutorName())) {
            // 중복 이름이 있는 경우 로그를 남기고 예외를 발생.
            log.warn("Tutor registration failed: Duplicate tutor name {}", requestDto.getTutorName());
            throw new IllegalStateException("Tutor with name " + requestDto.getTutorName() + " already exists");
        }
        // Tutor 엔티티 생성
        Tutor tutor = new Tutor(requestDto);

        // 저장 시도
        Tutor savedTutor;
        try {
            savedTutor = tutorRepository.save(tutor);
        } catch (DataAccessException e) {
            // 데이터베이스 액세스 중 예외 발생 시 로그를 발생
            log.error("Tutor registration failed: Database access error", e);
            throw e; // 예외를 상위 계층으로 전파
        }

        // 저장 성공 로그
        log.info("Tutor registered successfully with id: {}", savedTutor.getTutorId());

        // TutorResponseDto 생성 및 반환
        return new TutorResponseDto(savedTutor);
    }

    // 선택한 강사 조회
    public TutorResponseDto getTutor(Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강사는 없습니다."));
        return new TutorResponseDto(tutor);
    }

    // 강사 정보 수정
    @Transactional
    public TutorResponseDto updateTutor(Long tutorId, TutorRequestDto requestDto) {
        // 기존 Tutor 조회
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강사는 없습니다."));

        // 수정 사항 반영
        tutor.setExperienceYears(requestDto.getExperienceYears());
        tutor.setCompany(requestDto.getCompany());
        tutor.setPhoneNumber(requestDto.getPhoneNumber());
        tutor.setBio(requestDto.getBio());

        // 업데이트된 Tutor 저장
        Tutor updatedTutor = tutorRepository.save(tutor);

        // 업데이트된 정보로 ResponseDto 생성
        return new TutorResponseDto(updatedTutor);
    }
}
