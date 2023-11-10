package com.sparta.auth.entity;

import com.sparta.auth.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lectures")
public class Lecture extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private LectureEnum category;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Lecture(LectureRequestDto requestDto, Tutor tutor) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
        this.tutor = tutor;
    }
}
