package com.sparta.service;

import com.sparta.dto.LoginRequestDto;
import com.sparta.dto.LoginResponseDto;
import com.sparta.dto.SignUpRequestDto;
import com.sparta.dto.SignUpResponseDto;
import com.sparta.entity.User;
import com.sparta.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {

        // 이메일 중복 여부
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일 주소입니다.");
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        //사용자 등록 (DTO의 각 필드를 직접 전달하는 방식으로 수정)
        User user = new User(
                signUpRequestDto.getUserName(),
                signUpRequestDto.getEmail(),
                encryptedPassword,
                signUpRequestDto.getRole()
        );

        User saveUser = userRepository.save(user);

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto(saveUser);

        return signUpResponseDto;
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입된 사용자가 아닙니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 사용자 정보 반환
        return new LoginResponseDto(user);
    }

    // 로그아웃
    public String logout(Long id) {
        return "로그아웃되었습니다.";
    }
}
