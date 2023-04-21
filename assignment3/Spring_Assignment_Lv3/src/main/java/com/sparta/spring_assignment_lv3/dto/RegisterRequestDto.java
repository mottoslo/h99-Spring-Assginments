package com.sparta.spring_assignment_lv3.dto;

import lombok.Getter;

@Getter
public class RegisterRequestDto {
    private final String userid;
    private final String password;
    private final String username;
    private final String email;
    private final boolean isadmin;
    private String adminkey;

    public RegisterRequestDto(String userid, String password, String username, String email,
                              boolean isadmin, String adminKey
    ) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.email = email;
        this.isadmin = isadmin;
        this.adminkey = adminKey;
    }
}
