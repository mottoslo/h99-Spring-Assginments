package com.sparta.spring_assignment_lv2.entity;


import com.sparta.spring_assignment_lv2.dto.ArticleRequestDto;
import com.sparta.spring_assignment_lv2.dto.ArticleRequestDto;
import com.sparta.spring_assignment_lv2.entity.Timestamped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String password;
    private String content;

    public Article(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }

    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
}
