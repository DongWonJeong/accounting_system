package com.sparta.jwt;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JwtBlackList {

    // 블랙리스트를 저장할 Set
    private final Set<String> blacklistedTokens = new HashSet<>();

    // 토큰 추가
    public void addTokenToBlacklist(String token) {
        blacklistedTokens.add(token);
    }

    // 토큰이 블랙리스트에 있는지 확인
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
