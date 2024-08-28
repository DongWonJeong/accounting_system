package com.sparta.dto;

import com.sparta.entity.Role;
import com.sparta.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private  Long id;
    private String userName;
    private String email;
    private Role role;

    public SignUpResponseDto(User User) {
        this.id = User.getId();
        this.userName = User.getUserName();
        this.email = User.getEmail();
        this.role = User.getRole();
    }
}
