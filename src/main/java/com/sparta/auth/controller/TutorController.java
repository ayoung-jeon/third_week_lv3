package com.sparta.auth.controller;

import com.sparta.auth.dto.ProductRequestDto;
import com.sparta.auth.dto.TutorRequestDto;
import com.sparta.auth.dto.TutorResponseDto;
import com.sparta.auth.entity.UserRoleEnum;
import com.sparta.auth.security.UserDetailsImpl;
import com.sparta.auth.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    // 강사 등록
    @PostMapping("/tutor")
    @PreAuthorize("hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<TutorResponseDto> registerTutor(@Valid @RequestBody TutorRequestDto requestDto) {
            TutorResponseDto responseDto = tutorService.registerTutor(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 강사 조회
    @GetMapping("/tutor/{tutorId}")
    @PreAuthorize("hasAuthority('ROLE_STAFF') or hasAuthority('ROLE_MANAGER')")
    public TutorResponseDto getTutor(@PathVariable Long tutorId) {
        return tutorService.getTutor(tutorId);
    }

    @PutMapping("/tutor/{tutorId}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<TutorResponseDto> updateTutor(
            @PathVariable Long tutorId,
            @Valid @RequestBody TutorRequestDto requestDto) {

        TutorResponseDto responseDto = tutorService.updateTutor(tutorId, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
