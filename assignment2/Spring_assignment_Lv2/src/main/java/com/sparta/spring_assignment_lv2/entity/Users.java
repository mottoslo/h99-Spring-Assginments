package com.sparta.spring_assignment_lv2.entity;

import com.sparta.spring_assignment_lv2.dto.RegisterRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String userId;
    private String password;
    private String userName;
    private String email;

    public Users(RegisterRequestDto registerRequestDto) {
        this.userId = registerRequestDto.getUserid();
        this.password = registerRequestDto.getPassword();
        this.userName = registerRequestDto.getUsername();
        this.email = registerRequestDto.getEmail();
    }
}
