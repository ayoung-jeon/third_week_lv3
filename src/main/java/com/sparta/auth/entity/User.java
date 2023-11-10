package com.sparta.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true) // unique = true -> 중복을 막는다
    private String email;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)  // Enum 타입을 데이터 베이스 컬럼에 저장할 때 사용함
    private UserRoleEnum role;

    public User(String username, String password, String email, String department, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.department = department;
        this.role = role;
    }
}
