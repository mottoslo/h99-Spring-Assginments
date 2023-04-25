package com.sparta.spring_assignment_lv4.entity;


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
    private Role memberRole;


    public User(String userId, String password, Role memberRole) {
        this.userId = userId;
        this.password = password;
        this.memberRole = memberRole;
    }

}
