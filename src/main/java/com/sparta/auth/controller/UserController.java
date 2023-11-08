package com.sparta.auth.controller;

import com.sparta.auth.dto.SignupRequestDto;
import com.sparta.auth.entity.User;
import com.sparta.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        User responseDto = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
