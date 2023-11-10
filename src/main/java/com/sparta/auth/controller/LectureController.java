package com.sparta.auth.controller;

import com.sparta.auth.dto.LectureRequestDto;
import com.sparta.auth.dto.LectureResponseDto;
import com.sparta.auth.entity.LectureEnum;
import com.sparta.auth.service.LectureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    // 강의 등록
    @PostMapping("/Lecture")
    @PreAuthorize("hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<LectureResponseDto> registerLecture(@Valid @RequestBody LectureRequestDto requestDto) {
        LectureResponseDto responseDto = lectureService.registerLecture(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 강의 조회
    @GetMapping("/Lecture/{lectureId}")
    @PreAuthorize("hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_MANAGER')")
    public LectureResponseDto getLecture(@PathVariable Long lectureId) {
        return lectureService.getLecture(lectureId);
    }

    // 강의정 정보 수정
    @PutMapping("/Lecture/{lectureId}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<LectureResponseDto> updateLecture (
            @PathVariable Long lectureId,
            @Valid @RequestBody LectureRequestDto requestDto) {

        LectureResponseDto responseDto = lectureService.updateLecture(lectureId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 카테고리별 강의 목록 조회
    // 카테고리별 강의 목록 조회
    @GetMapping("/lectures/category/{category}")
    @PreAuthorize("hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<List<LectureResponseDto>> getLecturesByCategory(@PathVariable LectureEnum category) {
        List<LectureResponseDto> lectures = lectureService.getLecturesByCategory(category);
        return ResponseEntity.ok(lectures);
    }
}
