package com.sparta.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    // 토큰 만료 시간
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    // Base64 디코딩
    private byte[] getSecretKeyBytes() {
        return Base64.getDecoder().decode(secretKey);
    }

    // 토큰 생성
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, getSecretKeyBytes())
                .compact();
    }

    // 토큰에서 email 추출
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKeyBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 토큰 유효성 검사
    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSecretKeyBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token, String email) {
        return !isTokenExpired(token) && email.equals(getEmailFromToken(token));
    }
}