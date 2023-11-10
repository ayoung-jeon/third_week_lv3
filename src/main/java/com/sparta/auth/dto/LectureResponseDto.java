package com.sparta.auth.dto;

import com.sparta.auth.entity.Lecture;
import com.sparta.auth.entity.LectureEnum;
import com.sparta.auth.entity.Tutor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private String title;
    private BigDecimal price;
    private String description;
    private LectureEnum category;
    private Tutor tutor;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public LectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.tutor = lecture.getTutor();
        this.createdAt = lecture.getCreatedAt();
        this.modifiedAt = lecture.getModifiedAt();
    }
}
