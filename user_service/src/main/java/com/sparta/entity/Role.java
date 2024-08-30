package com.sparta.entity;

import lombok.Getter;

@Getter
public enum Role {

    USER("ROLE_USER"),
    SELLER("ROLE_SELLER");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }
}
