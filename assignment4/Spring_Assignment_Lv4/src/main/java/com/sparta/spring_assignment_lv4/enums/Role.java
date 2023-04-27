package com.sparta.spring_assignment_lv4.enums;

import org.springframework.security.core.Authentication;

import java.util.List;

public enum Role {
    ROLE_USER(Authorization.USER),
    ROLE_ADMIN(Authorization.ADMIN);

    private final String authorization;

    private Role(String authorization){
        this.authorization = authorization;
    }
    public String getAuthorization(){
        return authorization;
    }

    private class Authorization{
        private static final String USER = "USER";
        private static final String ADMIN = "ADMIN";
    }
}
