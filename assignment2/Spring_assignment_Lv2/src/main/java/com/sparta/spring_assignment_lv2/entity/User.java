package com.sparta.spring_assignment_lv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String password;
    private String name;
    @OneToMany

    private List<Article> articles = new ArrayList<>();

    public User(Long id, String userid, String password, String name) {
        this.userid = userid;
        this.password = password;
        this.name = name;
    }
}
