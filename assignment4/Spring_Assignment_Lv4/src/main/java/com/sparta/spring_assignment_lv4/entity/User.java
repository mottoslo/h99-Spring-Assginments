package com.sparta.spring_assignment_lv4.entity;


import com.sparta.spring_assignment_lv4.dto.SignupRequestDto;
import com.sparta.spring_assignment_lv4.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;


    public User(SignupRequestDto requestDto) {
        this.userId = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = requestDto.isAdmin() ? Role.ROLE_ADMIN : Role.ROLE_USER;
    }
}
