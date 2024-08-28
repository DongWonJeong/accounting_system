package com.sparta.entity;

import com.sparta.dto.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    public User(SignUpRequestDto signUpRequestDto) {
        this.userName = signUpRequestDto.getUserName();
        this.email = signUpRequestDto.getEmail();
        this.password = signUpRequestDto.getPassword();
        this.role = signUpRequestDto.getRole();
    }
}
