package com.sparta.controller;

import com.sparta.dto.info.UserInfoResponseDto;
import com.sparta.dto.login.LoginRequestDto;
import com.sparta.dto.login.LoginResponseDto;
import com.sparta.dto.signUp.SignUpRequestDto;
import com.sparta.dto.signUp.SignUpResponseDto;
import com.sparta.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 조회
    @GetMapping("/list")
    public List<UserInfoResponseDto> getUsers() {
       return userService.getUsers();
    }

    // 회원가입
    @PostMapping("/signup")
    public SignUpResponseDto signup(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        return userService.signup(signUpRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return userService.login(loginRequestDto);
    }

    // 로그아웃
    @PostMapping("/logout/{id}")
    public String logout(@PathVariable Long id, @RequestHeader("Authorization") String token){
        return userService.logout(id, token);
    }
}