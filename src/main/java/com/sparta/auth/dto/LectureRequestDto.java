package com.sparta.auth.dto;

import com.sparta.auth.entity.LectureEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureRequestDto {
    @NotBlank
    private String title;

    @DecimalMin(value = "0", inclusive = false)
    private Long price;

    @NotBlank
    private String description;

    @NonNull
    private LectureEnum category;

//    @NonNull
//    private Long tutorId;
}
