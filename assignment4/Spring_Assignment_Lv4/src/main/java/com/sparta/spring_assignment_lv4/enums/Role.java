package com.sparta.spring_assignment_lv4.enums;

import org.springframework.security.core.Authentication;

public enum Role {
    USER,ADMIN;

    private String authorization;


    public String getAuthorization(){
        return this.authorization;
    }
}
