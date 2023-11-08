package com.sparta.auth.service;

import com.sparta.auth.dto.SignupRequestDto;
import com.sparta.auth.entity.User;
import com.sparta.auth.entity.UserRoleEnum;
import com.sparta.auth.jwt.JwtUtil;
import com.sparta.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserRoleEnum role = UserRoleEnum.STAFF;
//        if (requestDto.isAdmin()) {
//            // 관리자 권한을 요청한 경우
//            // ADMIN_TOKEN -> 일반 사용자인지 관리자 인지를 구분하기 위해 만든것
//            String ADMIN_TOKEN = "qwertyuiop";
//            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            if (!"커리큘럼".equals(department) && !"개발".equals(department)) {
//                throw new IllegalArgumentException("MANAGER 권한은 커리큘럼, 개발 부서만 부여 받을 수 있습니다.");
//            }
//            role = UserRoleEnum.MANAGER; // 커리큘럼 또는 개발 부서일 경우 MANAGER 권한 부여
//        }

        // 사용자 등록
        User user = new User(username, password, email, department, role);
        userRepository.save(user);
        return user;
    }
}
