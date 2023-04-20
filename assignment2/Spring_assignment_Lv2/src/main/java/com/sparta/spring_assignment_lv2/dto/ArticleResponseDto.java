package com.sparta.spring_assignment_lv2.dto;


import com.sparta.spring_assignment_lv2.entity.Article;
import com.sparta.spring_assignment_lv2.entity.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String author;
    private String content;

    public ArticleResponseDto(Article article) {   //한번에 안되나 ?
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getUser().getUserId();
        this.content = article.getContent();
    }

}
