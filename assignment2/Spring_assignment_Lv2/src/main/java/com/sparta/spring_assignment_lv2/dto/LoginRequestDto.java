package com.sparta.spring_assignment_lv2.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String userid;
    private String password;

    public LoginRequestDto(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}
