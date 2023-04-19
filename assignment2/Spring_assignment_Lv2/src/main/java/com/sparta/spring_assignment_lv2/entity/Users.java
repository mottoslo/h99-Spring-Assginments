package com.sparta.spring_assignment_lv2.entity;

import com.sparta.spring_assignment_lv2.dto.RegisterRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String password;
    private String name;
    @OneToMany
    private List<Article> articles = new ArrayList<>();

    public Users(Long id, String userid, String password, String name) {
        this.userid = userid;
        this.password = password;
        this.name = name;
    }

    public Users(RegisterRequestDto registerRequestDto) {
        this.userid = registerRequestDto.getUserid();
        this.password = registerRequestDto.getPassword();
        this.name = registerRequestDto.getUsername();
    }
}
