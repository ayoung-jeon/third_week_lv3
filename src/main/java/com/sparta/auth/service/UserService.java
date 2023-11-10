package com.sparta.auth.service;

import com.sparta.auth.dto.SignupRequestDto;
import com.sparta.auth.entity.User;
import com.sparta.auth.entity.UserRoleEnum;
import com.sparta.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;

    public User signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String department = requestDto.getDepartment();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        // 부서명에 따라 역할을 할당
        UserRoleEnum role; // 역할을 저장할 변수 선언

        // 커리큘럼 또는 개발 부서인 경우 MANAGER 권한 부여
        if ("커리큘럼".equals(department) || "개발".equals(department)) {
            role = UserRoleEnum.MANAGER;
        } else {
            // 그 외의 경우는 STAFF 권한 부여
            role = UserRoleEnum.STAFF;
        }

        // 사용자 등록
        User user = new User(username, password, email, department, role);
        userRepository.save(user);
        return user;
    }
}
