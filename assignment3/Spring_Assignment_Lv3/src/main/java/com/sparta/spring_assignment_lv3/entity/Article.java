package com.sparta.spring_assignment_lv3.entity;


import com.sparta.spring_assignment_lv3.dto.ArticleRequestDto;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Article extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;            //Article 가져올 때 Users도 같이 가져와야하는데 효율적인가 ? 다른방법 ?

    public Article(ArticleRequestDto requestDto, Users user) {
        this.title = requestDto.getTitle();
        this.author = user.getUserId();
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
