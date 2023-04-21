package com.sparta.spring_assignment_lv3.entity;

import com.sparta.spring_assignment_lv3.dto.RegisterRequestDto;
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
    private Long Id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();
    public Users(RegisterRequestDto registerRequestDto) {
        this.userId = registerRequestDto.getUserid();
        this.password = registerRequestDto.getPassword();
        this.userName = registerRequestDto.getUsername();
        this.email = registerRequestDto.getEmail();
    }


    public void addArticle(Article article) {
        this.articles.add(article);
    }

    public void addComment(Comment comment) {
        this.comment.add(comment);
    }
}
