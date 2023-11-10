package com.sparta.auth.entity;

import com.sparta.auth.dto.TutorRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorId;

    @Column(nullable = false, unique = true) // unique = true -> 중복을 막는다
    private String tutorName;

    @Column(nullable = false)
    private Long experienceYears;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private Long phoneNumber;

    @Column(nullable = false)
    private String bio;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public Tutor(TutorRequestDto requestDto, Lecture lecture) {
        this.tutorName = requestDto.getTutorName();
        this.experienceYears = requestDto.getExperienceYears();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.bio = requestDto.getBio();
        this.lecture = lecture;
    }
}
