package com.sparta.auth.dto;

import com.sparta.auth.entity.Tutor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TutorResponseDto {
    private Long id;
    private String tutorName;
    private Long experienceYears;
    private String company;
    private Long phoneNumber;
    private String bio;

    public TutorResponseDto(Tutor tutor) {
        this.id = tutor.getId();
        this.tutorName = tutor.getTutorName();
        this.experienceYears = tutor.getExperienceYears();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.bio = tutor.getBio();
    }
}
