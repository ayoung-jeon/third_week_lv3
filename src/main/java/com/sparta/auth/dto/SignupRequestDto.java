package com.sparta.auth.dto;


import com.sparta.auth.entity.User;
import com.sparta.auth.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email(message = "이메일 형식에 맞게 입력하세요")
    @NotBlank
    private String email;

    @NotBlank
    private String department;

}
