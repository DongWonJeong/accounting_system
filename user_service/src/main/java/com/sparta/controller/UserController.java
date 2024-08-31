package com.sparta.controller;

import com.sparta.dto.LoginRequestDto;
import com.sparta.dto.LoginResponseDto;
import com.sparta.dto.SignUpRequestDto;
import com.sparta.dto.SignUpResponseDto;
import com.sparta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token){
        return userService.logout(token);
    }
//    @PostMapping("/logout")
//    public String logout(@RequestParam Long id){
//        return userService.logout(id);
//    }ㅋㅋ

}