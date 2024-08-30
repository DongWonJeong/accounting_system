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

    public SignUpResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
