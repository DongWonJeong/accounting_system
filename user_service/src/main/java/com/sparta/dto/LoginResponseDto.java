package com.sparta.dto;

import com.sparta.entity.Role;
import com.sparta.entity.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private String token;

    public LoginResponseDto(User user, String token) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.token = token;
    }
}
