package com.sparta.auth.dto;

import com.sparta.auth.entity.Lecture;
import com.sparta.auth.entity.Tutor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TutorResponseDto {
    private Long tutorId;
    private String tutorName;
    private Long experienceYears;
    private String company;
    private Long phoneNumber;
    private String bio;
    private Lecture lecture;

    public TutorResponseDto(Tutor tutor) {
        this.tutorId = tutor.getTutorId();
        this.tutorName = tutor.getTutorName();
        this.experienceYears = tutor.getExperienceYears();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.bio = tutor.getBio();
        this.lecture = tutor.getLecture();
    }
}
