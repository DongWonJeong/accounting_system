package com.sparta.service;

import com.sparta.dto.login.LoginRequestDto;
import com.sparta.dto.login.LoginResponseDto;
import com.sparta.dto.signUp.SignUpRequestDto;
import com.sparta.dto.signUp.SignUpResponseDto;
import com.sparta.entity.User;
import com.sparta.jwt.JwtBlackList;
import com.sparta.jwt.JwtUtil;
import com.sparta.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtBlackList jwtBlackList;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtBlackList jwtBlackList, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtBlackList = jwtBlackList;
        this.jwtUtil = jwtUtil;
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

        String token = jwtUtil.generateToken(user.getEmail());

        // 로그인 성공 시 사용자 정보 반환
        return new LoginResponseDto(user,token);
    }

    // 로그아웃
    public String logout(Long id, String token) {
        log.info("id: {}, token: {}", id, token);

        // JWT 토큰에서 사용자 이메일 추출
        // 로그아웃 요청을 처리할 때 사용자의 인증을 확인하고, 사용자 ID의 일치 여부를 검증하기 위함.
        String email = jwtUtil.getEmailFromToken(token);

        // 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입된 사용자가 아닙니다."));

        // 사용자 ID 검증
        if (!user.getId().equals(id)) {
            throw new IllegalArgumentException("사용자 ID가 일치하지 않습니다.");
        }

        // 블랙리스트에 토큰 추가
        jwtBlackList.addTokenToBlacklist(token);
        log.info("id: {}, token: {}", id, token);

        return "로그아웃되었습니다.";
    }
}
